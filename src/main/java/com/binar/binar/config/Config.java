package com.binar.binar.config;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Config {
    String code = "status", message = "message";

    public String code_sukses = "200";
    public String code_server = "500";
    public String code_null = "1";
    public String message_sukses = "sukses";
//    public String message_not_found = "not found ";

    public String message_alreadyexist = " already exist";

    public String message_constraint = "already exist ";

    public String message_eror = "";

    public String message_globalkesalahanlogika = "eror, ";

    public String message_null = " cannot be empty ";
    public String desc = "desc";
    public String asc = "asc";

    public String fineishTreasureHunt = "1";
    public String fineishVR = "2";

    public String message_head_detail = "";

    public String s_published_question_treasure = "5";
    public String s_draft_question_treasure = "4";
    public String s_NONpublished_question_treasure = "7";

    public String s_published_question_video = "2";
    public String s_draft_question_video = "1";
    public String s_NONpublished_question_video = "6";

    public String s_published_posttest = "9";
    public String s_draft_posttest = "8";
    public String s_NONpublished_posttest = "10";


    public String s_delete_question = "3";
    public String s_delete_VIDEO = "3";
    public String finishtoPlay = "1";

    public String status_unpublishsesigame = "3";

    public String maxuserZOna = "5";//4
    public String maxuserevent = "17";//16


    public String postTest = "1";

    public String ROLE_USER = "ROLE_USER";
    public String ROLE_ADMIN = "ROLE_ADMIN";
    public Boolean con_true = true;
    public Boolean con_false = false;


    public  String eventModeVR = "2";
    public  String eventModeAR = "1";
    public  String eventModeVRdanAR = "3";


//    public String EnkripsiPassword(String passwordDeskripsi){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
//        String hashedPassword = passwordEncoder.encode(passwordDeskripsi);
//        return  hashedPassword;
//
//    }

    public String getenkripsiPassword(String passwordDeskripsi) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(13);
        String hashedPassword = passwordEncoder.encode(passwordDeskripsi);
        return hashedPassword;

    }

//    public String getDecodePassword( String passwordDeskripsi, String passwordEnkripsi){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(13);
//       boolean cek =passwordEncoder.matches(passwordDeskripsi,passwordEnkripsi);
//        return  hashedPassword;
//
//    }


    public Timestamp getTimestamp() {//2019-10-03 11:06:47.469
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    public Timestamp getTimestampByDate(Date date) { //2019-10-03 11:06:47.485
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    public long getTimestamptoDate(Date timestimp) { //1570075607468
        Timestamp ts = new Timestamp(timestimp.getTime());
        return ts.getTime();
    }


    public Date getfromtoDateFilter(String date, int type) throws ParseException {
        try {

            String ambil = null;
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println("Timestamp: " + date);
            Long aaa = Long.parseLong(date);

            Timestamp ts = new Timestamp(aaa);

            Date date1 = new Date(ts.getTime());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String strDate = dateFormat.format(date1);
//            System.out.println("Date: " + strDate);

            if (type == 1) {
                ambil = strDate + " 00:00:01";
            } else if (type == 2) {
                ambil = strDate + " 23:59:59";
            }

//            System.out.println("ambil: " + ambil);

            Date d1 = df2.parse(ambil);
//        System.out.println("Date: " + d1);
//        System.out.println("Date in dd-MM-yyyy HH:mm:ss format is: "+df2.format(d2));

            return d1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }


    public String convertDateToString(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);
//        System.out.println("Date: " + strDate);
        return strDate;
    }

    public String convertDateToStringDate(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateFormat.format(date);
//        System.out.println("Date: " + strDate);
        return strDate;
    }

    public String convertDateToString_expired(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateFormat.format(date);
//        System.out.println("Date: " + strDate);
        return strDate;
    }

    public Date getDateNow() {
        Date date = new Date();

        return date;
    }

    public String sudahloginApps = "1";

    public Boolean sudahLOCK = true;
    public String mode_interview_video = "2";
    public String statustidaktampilsesi = "3";
    public String existingHP = "Phone Number is already exist.";
    public String existingEmail = "Email is already exist.";

    public String idUser = "User id cannot be empty.";
    public String message_max_user = "The number of users has exceeded the maximum limit.";
    public String foundUserProfile = "User Profile id not found.";
    public String foundQuestionId = "Question id not found.";
    public String idOptions = "Option id cannot be empty.";
    public String foundOptions = "Option id not found.";

    public String foundSesi = "Session id not found.";

    public String foundUserInterviewVideo = "Interview id not found.";
    public String foundZona = "Zone id not found.";
    public String foundTypeSoal = "The Type Question id not found.";
    public String foundTimer = "Timer id not found.";


    public String foundEventZona = "Zone Event id not found.";//

    public String foundVideo = "The video id not found.";//

    public String foundLogINterviewVideo = "Log Interview id not found.";//

    public String foundTeamPersonal = "Team Member id not found."; //
    public String foundTeam = "Team id not found.";
    public String foundEvent = "Event id not found.";

    public String idSesi = "The Answer cannot be empty.";
    public String foundUser = "User id not found.";

    public String uploadexcel = "Excel format does not match. Please use the format provided. In line 1 and colum 0 must contain Question and colum 8 must contain Question Type.";
    public String dateexpired = "Event date has expired.";
    public String hintandjawaban = "Ansswer and hint cannot in the same options. index to ";

    public String erordaf1 = "No zone found in question id ";
    public String erorgenerate = "A Session Team error occurred, please re-generate the edit event. If the User has played the game, the Event cannot be updated.";
    public String maxuser = "The maximum number of users in one zone is 4.";
    public String maxuserTeamFull = "Team member is full. Please choose another marker.";
    public String sudahvooting = "This user is already vooting in the zone ";
    public String totalmax = "Team member is full. Please choose another marker.";//""The number of users in the zone has exceeded the maximum limit. Maximum Total in zone is ";
     public String tidakditemukanmarker = "No Marker found ";
    public String usertidakterdaftaar = "Failed to publish. User not registered or insufficient.";//"Event failed to publish. Because user is not registered or user is insufficient.";
    public String generate = "Session error occured, please try again.";//"A team session error occurred, please re-generate the event edit.";
    public String totaluserkecil = "Total number of user cannot be smaller than user registered";//"The total number of Users in the event cannot be smaller than the users registered.";
    public String maxusersama = "The maximum user must be equal to the total Zone.";
    public String maxevent = "The maximum number of users for each event is 16.";
    public String tidakbisaregisusertelat = "You are not allowed to join this event because game already started.";
    public String tidakbisaBack  = "You can't use back button, game already started.";
    public String tidakbisaPublish = "Unpublish not allowed, game already started.";

    public String codeChange = "expuserandnohpagain";
    public String codeChar = "__";

    public String foundeventmode = " Event Mode cannot be empty. ";
    public String erorzona1 = "Incorrect marker detected. Now you are in ";//" The User zone does not match the scan marker question, the current user zone ";
//    public String erorzona2 = " and the scan marker zone is ";
    public String erordaf2 = ", please register first.";
    public String sudahmain = " cannot be updated, because you have already played on Treasure Hunt.";
    public String tidakteam1 = " not on the team ";
    public String tidakteam2 = " please choose another user.";
    public String sudahdaftar = " already registered in the zone ";
    public String silahkanpilihmarker = ". Please select a marker ";
    public String tidakatif = " not active, because max user 0. Please scan another zone.";
    public String belumassign = " not assigned to the zone. Please assign it to the zone first.";
    public String tidakada = " or marker is not active / present. Please scan another marker.";
    public String belumassing = " has not been assigned to the Event ";
    public String markerharusisi = " Code markers cannot be empty.";

    // bakti
//    bahasa indonesia

}
