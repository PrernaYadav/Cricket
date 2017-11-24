package com.example.prashantbehera.cricket;

/**
 * Created by Prashant Behera on 01-Nov-17.
 */

public class LiveMatch {

    private String matchOdds,currentTeam,teamName1,teamName2,session,over,crr,rrr;
    private String runTeam1,runTeam2,wicketTeam1,wicketteam2,overTeam1,overTeam2,batsMan1,batsMan2,boller;
    private String run1,run2,run3,run4,run5,run6,tag;

    public LiveMatch() {
    }


    public LiveMatch(String matchOdds, String currentTeam, String teamName1, String teamName2, String session, String over, String crr,
                     String rrr, String runTeam1, String runTeam2, String wicketTeam1, String wicketteam2, String overTeam1,
                     String overTeam2, String batsMan1, String batsMan2, String boller, String run1, String run2, String run3, String run4,
                     String run5, String run6) {
        this.matchOdds = matchOdds;
        this.currentTeam = currentTeam;
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.session=session;
        this.over=over;
        this.crr=crr;
        this.rrr=rrr;
        this.runTeam1=runTeam1;
        this.runTeam2=runTeam2;
        this.wicketTeam1=wicketTeam1;
        this.wicketteam2=wicketteam2;
        this.overTeam1=overTeam1;
        this.overTeam2=overTeam2;
        this.batsMan1=batsMan1;
        this.batsMan2=batsMan2;
        this.boller=boller;
        this.run1=run1;

        this.run2=run2;
        this.run3=run3;
        this.run4=run4;
        this.run5=run5;
        this.run6=run6;

    }
    public String getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(String matchOdds) {
        this.matchOdds = matchOdds;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(String currentTeam) {
        this.currentTeam = currentTeam;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getCrr() {
        return crr;
    }

    public void setCrr(String crr) {
        this.crr = crr;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }

    public String getRunTeam1() {
        return runTeam1;
    }

    public void setRunTeam1(String runTeam1) {
        this.runTeam1 = runTeam1;
    }

    public String getRunTeam2() {
        return runTeam2;
    }

    public void setRunTeam2(String runTeam2) {
        this.runTeam2 = runTeam2;
    }

    public String getWicketTeam1() {
        return wicketTeam1;
    }

    public void setWicketTeam1(String wicketTeam1) {
        this.wicketTeam1 = wicketTeam1;
    }

    public String getWicketteam2() {
        return wicketteam2;
    }

    public void setWicketteam2(String wicketteam2) {
        this.wicketteam2 = wicketteam2;
    }

    public String getOverTeam1() {
        return overTeam1;
    }

    public void setOverTeam1(String overTeam1) {
        this.overTeam1 = overTeam1;
    }

    public String getOverTeam2() {
        return overTeam2;
    }

    public void setOverTeam2(String overTeam2) {
        this.overTeam2 = overTeam2;
    }

    public String getBatsMan1() {
        return batsMan1;
    }

    public void setBatsMan1(String batsMan1) {
        this.batsMan1 = batsMan1;
    }

    public String getBatsMan2() {
        return batsMan2;
    }

    public void setBatsMan2(String batsMan2) {
        this.batsMan2 = batsMan2;
    }

    public String getBoller() {
        return boller;
    }

    public void setBoller(String boller) {
        this.boller = boller;
    }

    public String getRun1() {
        return run1;
    }

    public void setRun1(String run1) {
        this.run1 = run1;
    }

    public String getRun2() {
        return run2;
    }

    public void setRun2(String run2) {
        this.run2 = run2;
    }

    public String getRun3() {
        return run3;
    }

    public void setRun3(String run3) {
        this.run3 = run3;
    }

    public String getRun4() {
        return run4;
    }

    public void setRun4(String run4) {
        this.run4 = run4;
    }

    public String getRun5() {
        return run5;
    }

    public void setRun5(String run5) {
        this.run5 = run5;
    }

    public String getRun6() {
        return run6;
    }

    public void setRun6(String run6) {
        this.run6 = run6;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}
