package com.starbucks.back.user.infrastructure.template;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplateBuilder {
    public String buildVerificationEmail(String code) {
        return """
                <html>
                    <body style="font-family: 'Helvetica Neue', Arial, sans-serif; background-color: #f4f4f4; padding: 40px;">
                        <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
                            <div style="background-color: #00704A; padding: 24px; text-align: center;">
                                <img src="https://www.starbucks.co.kr/common/img/common/logo.png" alt="Starbucks Logo" style="height: 40px;">
                            </div>
                            <div style="padding: 32px 16px;">
                                <h2 style="color: #00704A; margin-bottom: 16px;">Starbucks 이메일 인증</h2>
                                <p style="font-size: 16px; color: #333;">아래 인증번호를 입력하여 이메일 인증을 완료해주세요.</p>
                                <div style="margin: 24px 0; text-align: center;">
                                    <span style="font-size: 28px; color: #00704A; font-weight: bold;">%s</span>
                                </div>
                                <p style="font-size: 14px; color: #888;">인증번호는 5분간 유효합니다.</p>
                            </div>
                            <div style="background-color: #f4f4f4; padding: 16px; text-align: center; font-size: 12px; color: #aaa;">
                                본 메일은 Spharos Academy에서 발송되었습니다.
                            </div>
                        </div>
                    </body>
                </html>
            """.formatted(code);
    }
}
