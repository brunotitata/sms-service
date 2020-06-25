package br.com.sms.dto;

public class SmsIdDTO {

    private String smsId;

    public SmsIdDTO(String smsId) {
        this.smsId = smsId;
    }

    public SmsIdDTO() {
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    @Override
    public String toString() {
        return "SmsIdDTO{" +
                "smsId='" + smsId + '\'' +
                '}';
    }
}
