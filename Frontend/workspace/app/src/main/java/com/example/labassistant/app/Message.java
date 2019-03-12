package com.example.labassistant.app;

public class Message {

    private int id;
    private String name;
    private String content;
    private String timeStamp;

    /**
     * @param givenId
     *      Given id
     * @param givenName
     *      Given name
     * @param givenContent
     *      Given content
     * @param givenTimeStamp
     *      Given Time
     */
    public Message(int givenId, String givenName, String givenContent, String givenTimeStamp){
        id = givenId;
        name = givenName;
        content = givenContent;
        timeStamp = givenTimeStamp;
    }

    /**
     * @return
     *      name
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     *      content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return
     *      time
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @return
     *      id
     */
    public int getId() {
        return id;
    }

    /**
     * @return
     *      tag
     */
    public String getTag(){
        return name + ", " + timeStamp;
    }
}
