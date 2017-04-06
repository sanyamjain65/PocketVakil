package com.app.pocketvakil.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appbulous on 26/5/16.
 */
public class FaqResponseBean implements Parcelable {

    List<String>ques=new ArrayList<String>();
    private String question;
    private String answer;
    private String mQuestion;
    private String mAnswre;
    int ErrorCode; String ResponseString;

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getResponseString() {
        return ResponseString;
    }

    public void setResponseString(String responseString) {
        ResponseString = responseString;
    }

    private List<FaqResponseBean> faqList;



    public FaqResponseBean(Parcel in) {
        ques = in.createStringArrayList();
        question = in.readString();
        answer = in.readString();
        faqList = in.createTypedArrayList(FaqResponseBean.CREATOR);
        ans = in.createStringArrayList();
        mQuestion=in.readString();
        mAnswre=in.readString();
    }




    public static final Creator<FaqResponseBean> CREATOR = new Creator<FaqResponseBean>() {
        @Override
        public FaqResponseBean createFromParcel(Parcel in) {
            return new FaqResponseBean(in);
        }

        @Override
        public FaqResponseBean[] newArray(int size) {
            return new FaqResponseBean[size];
        }
    };

    public FaqResponseBean() {

    }

    public List<String> getAns() {
        return ans;
    }

    public void setAns(List<String> ans) {
        this.ans = ans;
    }

    List<String>ans=new ArrayList<String>();

    public List<String> getQues() {
        return ques;
    }

    public void setQues(List<String> ques) {
        this.ques = ques;
    }




    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setFaqList(List<FaqResponseBean> faqList) {
        this.faqList = faqList;
    }

    public List<FaqResponseBean> getFaqList() {
        return faqList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(ques);
        dest.writeString(question);
        dest.writeString(answer);
        dest.writeTypedList(faqList);
        dest.writeStringList(ans);
        dest.writeString(mQuestion);
        dest.writeString(mAnswre);
    }
}
