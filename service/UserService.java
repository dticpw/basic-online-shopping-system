package com.zx.service;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.smtp.SMTPMessage;
import com.zx.bean.User;
import com.zx.dao.UserDao;

public class UserService {

	private UserDao ud = new UserDao();
	/**
	 * @param loginName
	 * @param passWord
	 * @return
	 * //根据账号密码获取用户信息
	 */
	public User getUserByNameAndPass(String loginName, String passWord) {
		
		return ud.getUserByNameAndPass(loginName,passWord);
	}
	/**
	 * @param user
	 * 保存用户信息
	 */
	public void saveUser(User user,String proPath) {
		// TODO Auto-generated method stub
		try {
			//通过uuid生成激活码
			String active = UUID.randomUUID().toString();
			user.setActive(active);
			
			ud.saveUser(user);
			//判断用户角色 如果是普通会员则发送邮件给用户
			if(user.getRole() == 1) {
				//发送邮件
				sendMail(user,proPath);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//这里注册失败这会给到控制层RegisterServlet.java中e.getMessage接收
			throw new RuntimeException("注册失败"+e.getMessage());
		}
	}
	
	/**
	 * 发送邮件
	 */
	private void sendMail(User user,String proPath) {
		try {
			// 1.创建Properties对象，用于封装邮件发送相关信息
			Properties props = new Properties();
			// 服务器地址
			props.setProperty("mail.smtp.host","smtp.126.com");
			// 服务器需要
			props.setProperty("mail.smtp.auth","true");
			
			// 2.创建Authenticator的实例，实现账户、密码的鉴全
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication("dt_icpw@126.com","RJVSCKGJYUXIVJYM");
				}
			};
			
			// 3.创建Session对象 此处是javamail里的session而不是javaweb里面的
			Session session = Session.getInstance(props, auth);
			
			// 4.创建SMTPMessage 提供session(连接)
			// 通过SMTPMessage制定收件人|发送人|邮件内容等信息
			SMTPMessage msg = new SMTPMessage(session);
			// 指定邮件的标题/主题
			msg.setSubject("用户注册激活邮件，请勿回复，按照提示进行激活即可");
			// 指定邮件的内容
			// target='_blank'参数指示点击链接会打开一个新的窗口
			// 这里先用127.0.0.1垫着 之后到用户主机上再获取用户的ip
			msg.setContent("<a href='http://127.0.0.1:8080"+proPath+"/userActive.action?active="+user.getActive()+"' target='_blank'>点击该链接进行激活</a>","text/html;charset=UTF-8");
			// 指定发件人
			msg.setFrom(new InternetAddress("dt_icpw@126.com"));
			// 指定收件人 TO(收件人) CC(抄送) BCC(密送)
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			//发送邮件
//			System.out.println("信息装填完毕，开始发送邮件~");
			Transport.send(msg);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @param activeCode
	 * 用户信息激活
	 */
	public void activeUser(String activeCode) {
		// TODO Auto-generated method stub
		ud.activeUser(activeCode);
		
	}
	/**
	 * @param loginName
	 * @return
	 * 根据账号获取用户信息
	 */
	public String getUserByName(String loginName) {
		
		boolean flag = ud.getUserByName(loginName);
		return flag ? "用户已存在" : "";
	}
}






