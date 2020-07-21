package com.example.framework.work.entity.resp;

public class UserInfoResp {

    @Override
    public String toString() {
        return "UserInfoResp{" +
                "authen=" + authen +
                ", authenStatus=" + authenStatus +
                ", bid=" + bid +
                ", busStatus=" + busStatus +
                ", busname='" + busname + '\'' +
                ", hasBusiness=" + hasBusiness +
                ", hasPayWord=" + hasPayWord +
                ", headurl='" + headurl + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", levelName='" + levelName + '\'' +
                ", levelid=" + levelid +
                ", moneyUrl='" + moneyUrl + '\'' +
                ", msgNum=" + msgNum +
                ", myFreezePoint=" + myFreezePoint +
                ", myMoney=" + myMoney +
                ", myPoint=" + myPoint +
                ", myQrUrl='" + myQrUrl + '\'' +
                ", myShare=" + myShare +
                ", myVoucher=" + myVoucher +
                ", nickName='" + nickName + '\'' +
                ", offBusiness=" + offBusiness +
                ", payFreezeMoney=" + payFreezeMoney +
                ", payMoney=" + payMoney +
                ", phone='" + phone + '\'' +
                ", refereeName='" + refereeName + '\'' +
                ", sign=" + sign +
                '}';
    }

    /**
     * authen : 1
     * authenStatus : 2
     * bid : 136
     * busStatus : 1
     * busname : Lee
     * hasBusiness : 1
     * hasPayWord : 1
     * headurl : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL7asC8yDiayk3shHicvoJUibPb0vfoK909EJQAO1rickTiaHzEicIv2Z9bLUiawuG9xr1Inr65qXNSGibqvQ/132
     * inviteCode : Vtti1Vq4
     * levelName : 会员
     * levelid : 2
     * moneyUrl : 8tdGwFAT40QBeY1lSWjsiOh9UX6MLPzVEbfqZ3umxa
     * msgNum : 0
     * myFreezePoint : 0.0
     * myMoney : 4.1
     * myPoint : 1.42
     * myQrUrl : http://img.powertrend.cn/159359431497520828
     * myShare : 0.0
     * myVoucher : 47.46
     * nickName : Lee
     * offBusiness : 0
     * payFreezeMoney : 0.0
     * payMoney : 5600.0
     * phone : 15921804762
     * refereeName : wm
     * sign : 0
     */

    private int authen;
    private int authenStatus;
    private int bid;
    private int busStatus;
    private String busname;
    private int hasBusiness;
    private int hasPayWord;
    private String headurl;
    private String inviteCode;
    private String levelName;
    private int levelid;
    private String moneyUrl;
    private int msgNum;
    private double myFreezePoint;
    private double myMoney;
    private double myPoint;
    private String myQrUrl;
    private double myShare;
    private double myVoucher;
    private String nickName;
    private int offBusiness;
    private double payFreezeMoney;
    private double payMoney;
    private String phone;
    private String refereeName;
    private int sign;

    public int getAuthen() {
        return authen;
    }

    public void setAuthen(int authen) {
        this.authen = authen;
    }

    public int getAuthenStatus() {
        return authenStatus;
    }

    public void setAuthenStatus(int authenStatus) {
        this.authenStatus = authenStatus;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(int busStatus) {
        this.busStatus = busStatus;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public int getHasBusiness() {
        return hasBusiness;
    }

    public void setHasBusiness(int hasBusiness) {
        this.hasBusiness = hasBusiness;
    }

    public int getHasPayWord() {
        return hasPayWord;
    }

    public void setHasPayWord(int hasPayWord) {
        this.hasPayWord = hasPayWord;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getLevelid() {
        return levelid;
    }

    public void setLevelid(int levelid) {
        this.levelid = levelid;
    }

    public String getMoneyUrl() {
        return moneyUrl;
    }

    public void setMoneyUrl(String moneyUrl) {
        this.moneyUrl = moneyUrl;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public double getMyFreezePoint() {
        return myFreezePoint;
    }

    public void setMyFreezePoint(double myFreezePoint) {
        this.myFreezePoint = myFreezePoint;
    }

    public double getMyMoney() {
        return myMoney;
    }

    public void setMyMoney(double myMoney) {
        this.myMoney = myMoney;
    }

    public double getMyPoint() {
        return myPoint;
    }

    public void setMyPoint(double myPoint) {
        this.myPoint = myPoint;
    }

    public String getMyQrUrl() {
        return myQrUrl;
    }

    public void setMyQrUrl(String myQrUrl) {
        this.myQrUrl = myQrUrl;
    }

    public double getMyShare() {
        return myShare;
    }

    public void setMyShare(double myShare) {
        this.myShare = myShare;
    }

    public double getMyVoucher() {
        return myVoucher;
    }

    public void setMyVoucher(double myVoucher) {
        this.myVoucher = myVoucher;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getOffBusiness() {
        return offBusiness;
    }

    public void setOffBusiness(int offBusiness) {
        this.offBusiness = offBusiness;
    }

    public double getPayFreezeMoney() {
        return payFreezeMoney;
    }

    public void setPayFreezeMoney(double payFreezeMoney) {
        this.payFreezeMoney = payFreezeMoney;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}
