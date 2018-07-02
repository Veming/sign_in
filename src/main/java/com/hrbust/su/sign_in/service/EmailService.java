package com.hrbust.su.sign_in.service;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    public String sendMessage(String to, String username, String code){
        // 发件人电子邮箱
        String from = "1159288455@qq.com";
        // 指定发送邮件的主机为 smtp.qq.com
        //QQ 邮件服务器
        String host = "smtp.qq.com";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication()
            {
                //发件人邮件用户名、密码
                return new PasswordAuthentication(from, "azxcmxgsrirtfgbc");
            }
        });
        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // Set Subject: 头部头字段
            message.setSubject("哈尔滨理工大学 -学生签到系统 —— 验证code");
            // 设置消息体
            message.setText("测试");
            message.setContent(setMessageInfo(username,code),"text/html;charset=utf-8");
            // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
            // 发送消息
            Transport.send(message);
            System.out.println("邮件发送成功");
        }catch (MessagingException | GeneralSecurityException mex) {
            mex.printStackTrace();
            return "fail";
        }
        return "success";
    }

    private String setMessageInfo(String username, String code){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  "<br>\n" +
                "<div>\n" +
                "    <includetail>\n" +
                "        <style type=\"text/css\">\n" +
                "            body {\n" +
                "                margin: 0 auto;\n" +
                "                padding: 0;\n" +
                "                font-family: Microsoft Yahei, Tahoma, Arial;\n" +
                "                color: #333333;\n" +
                "                background-color: #fff;\n" +
                "                font-size: 12px;\n" +
                "            }\n" +
                "\n" +
                "            a {\n" +
                "                color: #00a2ca;\n" +
                "                line-height: 22px;\n" +
                "                text-decoration: none;\n" +
                "            }\n" +
                "\n" +
                "            a:hover {\n" +
                "                text-decoration: underline;\n" +
                "                color: #00a2ca;\n" +
                "            }\n" +
                "\n" +
                "            td {\n" +
                "                font-family: 'Microsoft YaHei';\n" +
                "            }\n" +
                "        </style>\n" +
                "        <table style=\"font-family:'Microsoft YaHei';\" width=\"800\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table width=\"800\" height=\"40\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\"></table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table style=\"font-family:'Microsoft YaHei';\" width=\"800\" height=\"48\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" bgcolor=\"#373d41\"\n" +
                "                            align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td colspan=\"2\" style=\"color:#ffffff; padding-right:20px;\" width=\"703\" valign=\"middle\" height=\"48\" align=\"right\">\n" +
                "                                        <a href=\"#\"\n" +
                "                                            target=\"_blank\" style=\"color:#ffffff;text-decoration:none;font-family:'Microsoft YaHei';\">哈尔滨理工大学软件学院 - 学生签到系统</a>\n" +
                "                                        &nbsp;&nbsp;\n" +
                "                                        <span style=\"color:#6c7479;\">|</span>&nbsp;&nbsp;\n" +
                "                                     </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table style=\" border:1px solid #edecec; border-top:none; border-bottom:none; padding:0 20px;font-size:14px;color:#333333;\"\n" +
                "                            width=\"800\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"left\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td border=\"0\" colspan=\"2\" style=\" font-size:16px;vertical-align:bottom;font-family:'Microsoft YaHei';\" width=\"760\" height=\"56\"\n" +
                "                                        align=\"left\">尊敬的 "+username+" \n" +
                "                                        <a></a>：</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td border=\"0\" colspan=\"2\" width=\"760\" height=\"30\" align=\"left\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td border=\"0\" style=\" width:40px; text-align:left;vertical-align:middle; line-height:32px; float:left;\" width=\"40\" valign=\"middle\"\n" +
                "                                        height=\"32\" align=\"left\"></td>\n" +
                "                                    <td border=\"0\" style=\" width:720px; text-align:left;vertical-align:middle;line-height:32px;font-family:'Microsoft YaHei';\"\n" +
                "                                        width=\"720\" valign=\"middle\" height=\"32\" align=\"left\">您于"+sdf.format(date)+"获取到的验证code为：</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td border=\"0\" style=\" width:40px; text-align:left;vertical-align:middle; line-height:32px; float:left;\" width=\"40\" valign=\"middle\"\n" +
                "                                        height=\"32\" align=\"left\"></td>\n" +
                "                                    <td border=\"0\" style=\" width:720px; text-align:left;vertical-align:middle;line-height:32px;font-family:'Microsoft YaHei';\"\n" +
                "                                        width=\"720\" valign=\"middle\" height=\"32\" align=\"left\">\n" +
                "                                        <table style=\"margin-left:20px;margin-top:10px;font-size:12px;font-weight:normal;border-collapse:collapse;\" width=\"90%\" cellspacing=\"0\"\n" +
                "                                            cellpadding=\"0\" bordercolor=\"#666666\" border=\"0\" bgcolor=\"#FFFFFF\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr style=\"background-color:#e4e4e4;\">\n" +
                "                                                    <th style=\"border:1px solid #ccc; height:32px;\" width=\"100%\">\n" +
                "                                                        验证code</th>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td style=\"border:1px solid #ccc;word-break:break-all;padding:4px\">\n" +
                "                                                        "+code+"</td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                        <br>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td colspan=\"2\" style=\"padding-left:40px;\" width=\"720\" height=\"32\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td colspan=\"2\" style=\"padding-left:40px;\" width=\"720\" height=\"32\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td colspan=\"2\" style=\"padding-left:40px;\" width=\"720\" height=\"32\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td colspan=\"2\" style=\"padding-bottom:16px; border-bottom:1px dashed #e5e5e5;font-family:'Microsoft YaHei';\" width=\"720\"\n" +
                "                                        height=\"14\">哈尔滨理工大学软件学院 - 学生签到系统</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td colspan=\"2\" style=\"padding:8px 0 28px;color:#999999; font-size:12px;font-family:'Microsoft YaHei';\" width=\"720\" height=\"14\">此为系统邮件请勿回复</td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </includetail>\n" +
                "</div>";
    }
}
