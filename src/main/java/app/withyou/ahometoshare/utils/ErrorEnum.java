package app.withyou.ahometoshare.utils;

public enum ErrorEnum {
    /*
     * Error message
     * */
    E_400("400", "Fail to process your request. Please try it later"),
    E_500("500", "Unexpected Access Request"),
    E_502("502", "Insufficient Privilege"),
    E_10009("10009", "Account already exist"),
    E_20011("20011", "Expired Session");


    private String errorCode;

    private String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}