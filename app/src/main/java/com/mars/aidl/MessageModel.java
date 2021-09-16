package com.mars.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MessageModel implements Parcelable {

    private String from;
    private String to;
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.from);
        dest.writeString(this.to);
        dest.writeString(this.content);
    }

    public void readFromParcel(Parcel source) {
        this.from = source.readString();
        this.to = source.readString();
        this.content = source.readString();
    }

    public MessageModel() {
    }

    protected MessageModel(Parcel in) {
        this.from = in.readString();
        this.to = in.readString();
        this.content = in.readString();
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel source) {
            return new MessageModel(source);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };
}
