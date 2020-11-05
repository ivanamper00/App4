package com.boltu.myapplication.model;


import com.boltu.myapplication.model.games.VenueModel;
import com.boltu.myapplication.model.series.SeriesListModel;
import com.boltu.myapplication.model.teams.TeamsListModel;
import com.google.gson.annotations.SerializedName;

public class MatchDetailsModel {
    @SerializedName("match")
    private Matches match;

    public MatchDetailsModel(Matches match) {
        this.match = match;
    }

    public static class Matches{
            @SerializedName("id")
            private Integer id;
            @SerializedName("matchTypeId")
            private Integer matchTypeId;
            @SerializedName("statisticsProvider")
            private String statisticsProvider;
            @SerializedName("series")
            private SeriesListModel series;
            @SerializedName("name")
            private String name;
            @SerializedName("status")
            private String status;
            @SerializedName("venue")
            private VenueModel venue;
            @SerializedName("homeTeam")
            private TeamsListModel homeTeam;
            @SerializedName("awayTeam")
            private TeamsListModel awayTeam;
            @SerializedName("currentMatchState")
            private String currentMatchState;
            @SerializedName("isMultiDay")
            private Boolean isMultiDay;
            @SerializedName("matchSummaryText")
            private String matchSummaryText;
            @SerializedName("scores")
            private Score scores;
            @SerializedName("isLive")
            private Boolean isLive;
            @SerializedName("currentInningId")
            private Integer currentInningId;
            @SerializedName("isMatchDrawn")
            private Boolean isMatchDrawn;
            @SerializedName("isMatchAbandoned")
            private Boolean isMatchAbandoned;
            @SerializedName("winningTeamId")
            private Integer winningTeamId;
            @SerializedName("startDateTime")
            private String startDateTime;
            @SerializedName("endDateTime")
            private String endDateTime;
            @SerializedName("localStartDate")
            private String localStartDate;
            @SerializedName("localStartTime")
            private String localStartTime;
            @SerializedName("isWomensMatch")
            private Boolean isWomensMatch;
            @SerializedName("cmsMatchType")
            private String cmsMatchType;
            @SerializedName("cmsMatchAssociatedType")
            private String cmsMatchAssociatedType;
            @SerializedName("cmsMatchVenueStartDateTime")
            private String cmsMatchVenueStartDateTime;
            @SerializedName("cmsMatchVenueEndDateTime")
            private String cmsMatchVenueEndDateTime;
            @SerializedName("cmsMatchStartDate")
            private String cmsMatchStartDate;
            @SerializedName("cmsMatchEndDate")
            private String cmsMatchEndDate;
            @SerializedName("gamedayStatus")
            private String gamedayStatus;
            @SerializedName("isGamedayEnabled")
            private Boolean isGamedayEnabled;
            @SerializedName("removeMatch")
            private Boolean removeMatch;

        public Matches(Integer id, Integer matchTypeId, String statisticsProvider, SeriesListModel series, String name, String status, VenueModel venue, TeamsListModel homeTeam, TeamsListModel awayTeam, String currentMatchState, Boolean isMultiDay, String matchSummaryText, Score scores, Boolean isLive, Integer currentInningId, Boolean isMatchDrawn, Boolean isMatchAbandoned, Integer winningTeamId, String startDateTime, String endDateTime, String localStartDate, String localStartTime, Boolean isWomensMatch, String cmsMatchType, String cmsMatchAssociatedType, String cmsMatchVenueStartDateTime, String cmsMatchVenueEndDateTime, String cmsMatchStartDate, String cmsMatchEndDate, String gamedayStatus, Boolean isGamedayEnabled, Boolean removeMatch) {
            this.id = id;
            this.matchTypeId = matchTypeId;
            this.statisticsProvider = statisticsProvider;
            this.series = series;
            this.name = name;
            this.status = status;
            this.venue = venue;
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.currentMatchState = currentMatchState;
            this.isMultiDay = isMultiDay;
            this.matchSummaryText = matchSummaryText;
            this.scores = scores;
            this.isLive = isLive;
            this.currentInningId = currentInningId;
            this.isMatchDrawn = isMatchDrawn;
            this.isMatchAbandoned = isMatchAbandoned;
            this.winningTeamId = winningTeamId;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.localStartDate = localStartDate;
            this.localStartTime = localStartTime;
            this.isWomensMatch = isWomensMatch;
            this.cmsMatchType = cmsMatchType;
            this.cmsMatchAssociatedType = cmsMatchAssociatedType;
            this.cmsMatchVenueStartDateTime = cmsMatchVenueStartDateTime;
            this.cmsMatchVenueEndDateTime = cmsMatchVenueEndDateTime;
            this.cmsMatchStartDate = cmsMatchStartDate;
            this.cmsMatchEndDate = cmsMatchEndDate;
            this.gamedayStatus = gamedayStatus;
            this.isGamedayEnabled = isGamedayEnabled;
            this.removeMatch = removeMatch;
        }

        public static class Score{
                    @SerializedName("homeScore")
                    private String homeScore;
                    @SerializedName("homeOvers")
                    private String homeOvers;
                    @SerializedName("awayScore")
                    private String awayScore;
                    @SerializedName("awayOvers")
                    private String awayOvers;

                public Score(String homeScore, String homeOvers, String awayScore, String awayOvers) {
                    this.homeScore = homeScore;
                    this.homeOvers = homeOvers;
                    this.awayScore = awayScore;
                    this.awayOvers = awayOvers;
                }

                public String getHomeScore() {
                        return homeScore;
                    }

                    public String getHomeOvers() {
                        return homeOvers;
                    }

                    public String getAwayScore() {
                        return awayScore;
                    }

                    public String getAwayOvers() {
                        return awayOvers;
                    }
                }

            public SeriesListModel getSeries() {
                return series;
            }

            public VenueModel getVenue() {
                return venue;
            }

            public Integer getId() {
                return id;
            }

            public Integer getMatchTypeId() {
                return matchTypeId;
            }

            public String getStatisticsProvider() {
                return statisticsProvider;
            }

            public String getName() {
                return name;
            }

            public String getStatus() {
                return status;
            }

            public TeamsListModel getHomeTeam() {
                return homeTeam;
            }

            public TeamsListModel getAwayTeam() {
                return awayTeam;
            }

            public String getCurrentMatchState() {
                return currentMatchState;
            }

            public Boolean getMultiDay() {
                return isMultiDay;
            }

            public String getMatchSummaryText() {
                return matchSummaryText;
            }

            public Score getScores() {
                return scores;
            }

            public Boolean getLive() {
                return isLive;
            }

            public Integer getCurrentInningId() {
                return currentInningId;
            }

            public Boolean getMatchDrawn() {
                return isMatchDrawn;
            }

            public Boolean getMatchAbandoned() {
                return isMatchAbandoned;
            }

            public Integer getWinningTeamId() {
                return winningTeamId;
            }

            public String getStartDateTime() {
                return startDateTime;
            }

            public String getEndDateTime() {
                return endDateTime;
            }

            public String getLocalStartDate() {
                return localStartDate;
            }

            public String getLocalStartTime() {
                return localStartTime;
            }

            public Boolean getWomensMatch() {
                return isWomensMatch;
            }

            public String getCmsMatchType() {
                return cmsMatchType;
            }

            public String getCmsMatchAssociatedType() {
                return cmsMatchAssociatedType;
            }

            public String getCmsMatchVenueStartDateTime() {
                return cmsMatchVenueStartDateTime;
            }

            public String getCmsMatchVenueEndDateTime() {
                return cmsMatchVenueEndDateTime;
            }

            public String getCmsMatchStartDate() {
                return cmsMatchStartDate;
            }

            public String getCmsMatchEndDate() {
                return cmsMatchEndDate;
            }

            public String getGamedayStatus() {
                return gamedayStatus;
            }

            public Boolean getGamedayEnabled() {
                return isGamedayEnabled;
            }

            public Boolean getRemoveMatch() {
                return removeMatch;
            }
        }

    public Matches getMatches() {
        return match;
    }
}

