package com.codedisaster.steamworks;


public enum SteamUGC$UGCQueryType {

   RankedByVote("RankedByVote", 0),
   RankedByPublicationDate("RankedByPublicationDate", 1),
   AcceptedForGameRankedByAcceptanceDate("AcceptedForGameRankedByAcceptanceDate", 2),
   RankedByTrend("RankedByTrend", 3),
   FavoritedByFriendsRankedByPublicationDate("FavoritedByFriendsRankedByPublicationDate", 4),
   CreatedByFriendsRankedByPublicationDate("CreatedByFriendsRankedByPublicationDate", 5),
   RankedByNumTimesReported("RankedByNumTimesReported", 6),
   CreatedByFollowedUsersRankedByPublicationDate("CreatedByFollowedUsersRankedByPublicationDate", 7),
   NotYetRated("NotYetRated", 8),
   RankedByTotalVotesAsc("RankedByTotalVotesAsc", 9),
   RankedByVotesUp("RankedByVotesUp", 10),
   RankedByTextSearch("RankedByTextSearch", 11),
   RankedByTotalUniqueSubscriptions("RankedByTotalUniqueSubscriptions", 12),
   RankedByPlaytimeTrend("RankedByPlaytimeTrend", 13),
   RankedByTotalPlaytime("RankedByTotalPlaytime", 14),
   RankedByAveragePlaytimeTrend("RankedByAveragePlaytimeTrend", 15),
   RankedByLifetimeAveragePlaytime("RankedByLifetimeAveragePlaytime", 16),
   RankedByPlaytimeSessionsTrend("RankedByPlaytimeSessionsTrend", 17),
   RankedByLifetimePlaytimeSessions("RankedByLifetimePlaytimeSessions", 18);
   // $FF: synthetic field
   private static final SteamUGC$UGCQueryType[] $VALUES = new SteamUGC$UGCQueryType[]{RankedByVote, RankedByPublicationDate, AcceptedForGameRankedByAcceptanceDate, RankedByTrend, FavoritedByFriendsRankedByPublicationDate, CreatedByFriendsRankedByPublicationDate, RankedByNumTimesReported, CreatedByFollowedUsersRankedByPublicationDate, NotYetRated, RankedByTotalVotesAsc, RankedByVotesUp, RankedByTextSearch, RankedByTotalUniqueSubscriptions, RankedByPlaytimeTrend, RankedByTotalPlaytime, RankedByAveragePlaytimeTrend, RankedByLifetimeAveragePlaytime, RankedByPlaytimeSessionsTrend, RankedByLifetimePlaytimeSessions};


   private SteamUGC$UGCQueryType(String var1, int var2) {}

}
