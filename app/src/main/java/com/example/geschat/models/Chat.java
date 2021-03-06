package com.example.geschat.models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//Adapter de RecyclerView del fragment de Chats

public class Chat {

    private String chatName,keyDB;
    private Boolean finished,proposalApproved, isFilled;
    private String level,facilitator,presentation,comments,date,facilitatorName,status,debug, rejected;
    int amountPeople;
    private ArrayList<String> waitList, assistanceList;
    private String startTime, endTime;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getStatus() {
        return status;
    }

    public String getKeyDB() {
        return keyDB;
    }

    public void setKeyDB(String keyDB) {
        this.keyDB = keyDB;
    }

    public Chat(String chatName, Boolean finished){
        this.chatName = chatName;
        this.finished = finished;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public static int getLastContactId() {
        return lastContactId;
    }

    public static void setLastContactId(int lastContactId) {
        Chat.lastContactId = lastContactId;
    }

    public String getFacilitatorName() {
        return facilitatorName;
    }

    public void setFacilitatorName(String facilitatorName) {
        this.facilitatorName = facilitatorName;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }

    //Constructor principal
    public Chat(ArrayList<String> assistanceList, Boolean proposalApproved, String date, String facilitator, String comments, Boolean finished, Boolean isFilled, String presentation, String chatName, String level, int amountPeople, String startTime, String endTime){
        this.assistanceList = assistanceList;
        this.proposalApproved = proposalApproved;
        this.date = date;
        this.facilitator = facilitator;
        this.comments = comments;
        this.finished = finished;
        this.isFilled = isFilled;
        this.presentation = presentation;
        this.chatName = chatName;
        this.level = level;
        this.amountPeople = amountPeople;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Chat(String chatName, Boolean finished, Boolean proposalApproved, Boolean isFilled, String level, String facilitator, String presentation, String comments, String date) {
        this.chatName = chatName;
        this.finished = finished;
        this.proposalApproved = proposalApproved;
        this.isFilled = isFilled;
        this.level = level;
        this.facilitator = facilitator;
        this.presentation = presentation;
        this.comments = comments;
        this.date = date;
    }

    //CHATREVIEW CONSTRUCTOR
    public Chat(String chatName, Boolean finished, String facilitador, String nivel, String fecha ){
        this.chatName = chatName;
        this.finished = finished;
        this.facilitator = facilitador;
        this.level = nivel;
        this.date = fecha;
    }

    //IncomingChat - FacChatCompleted
    public Chat(int inscritos, String nombreChat, String nombreFacilitador, String nivel, String fecha) {
        this.amountPeople = inscritos;
        this.chatName = nombreChat;
        this.facilitatorName = nombreFacilitador;
        this.level = nivel;
        this.date = fecha;
    }

    //ChatRevisionStatus
    public Chat(String chatNombre, String facNombre, String fecha, String status, String nivel) {
        this.chatName = chatNombre;
        this.facilitatorName = facNombre;
        this.date = fecha;
        this.status = status;
        this.level = nivel;
    }

    //DEBUG

    public Chat(int inscritos, String nombreChat, String nombreFacilitador, String nivel, String fecha, boolean finished) {
        this.amountPeople = inscritos;
        this.chatName = nombreChat;
        this.facilitatorName = nombreFacilitador;
        this.level = nivel;
        this.date = fecha;
        this.finished = finished;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getProposalApproved() {
        return proposalApproved;
    }

    public void setProposalApproved(Boolean proposalApproved) {
        this.proposalApproved = proposalApproved;
    }

    public Boolean getFilled() {
        return isFilled;
    }

    public void setFilled(Boolean filled) {
        isFilled = filled;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getWaitList() {
        return waitList;
    }

    public void setWaitList(ArrayList<String> waitList) {
        this.waitList = waitList;
    }

    public ArrayList<String> getAssistanceList() {
        return assistanceList;
    }

    public void setAssistanceList(ArrayList<String> assistanceList) {
        this.assistanceList = assistanceList;
    }

    public String getChatName() {
        return chatName;
    }

    public boolean isFinished() {
        return finished;
    }

    //generar unos chats pa proba -- BORRAR despues

    private static int lastContactId =0;

    public static ArrayList<Chat> createChatList(int numChats) {
        ArrayList<Chat> chatList = new ArrayList<Chat>();

        for (int i = 1; i <= numChats; i++) {
            //CHAT HOME PAGE
            chatList.add(new Chat("Chat #" + ++lastContactId, i % 2 == 0 ));
            //REVIEW MODEL
            chatList.add(new Chat("Chat #" + ++lastContactId, i % 2 == 0 ,"Random", "HardCore","01/6/2079"));
        }

        return chatList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }



    //TESTING
   public static Comparator<Chat> ByStatus = new Comparator<Chat>() {
        @Override
        public int compare(Chat o1, Chat o2) {

            int b1 = o1.isFilled ? 1: 0;
            int b2 = o2.isFilled ? 1:0;
            return -(b2-b1);

           /* return Boolean.compare(o1.finished,o2.finished);*/

           /* return -Integer.valueOf(o1.date).compareTo(Integer.valueOf(o2.date));*/
        }
    };



    public static Comparator<Chat> ByUsers = new Comparator<Chat>() {
        @Override
        public int compare(Chat o1, Chat o2) {

            String a =  Integer.toString(o1.amountPeople);
            String b =  Integer.toString(o2.amountPeople);

            return -(Integer.valueOf(a).compareTo(Integer.valueOf(b)));

            /* return Boolean.compare(o1.finished,o2.finished);*/

            /* return -Integer.valueOf(o1.date).compareTo(Integer.valueOf(o2.date));*/
        }
    };



}
