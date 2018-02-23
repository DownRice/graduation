package cn.downrice.graduation.library.utils;

/**
 * 用于生成唯一Redis Key
 * @author 下饭
 */
public class RedisKeyUtils {
    private static String SPLIT = "-";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";
    private static String BIZ_EVENTQUEUE = "EVENT_QUEUE";

    /**
     * 粉丝列表
     */
    private static String BIZ_FOLLOWER = "FOLLOWER";
    /**
     * 关注列表
     */
    private static String BIZ_FOLLOWEE = "FOLLOWEE";

    private static String BIZ_VARIFY = "VARIFY";

    public static String getLikeKey(int entityType, int entityId){
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String getDislikeKey(int entityType, int entityId){
        return BIZ_DISLIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String getEventQueueKey(){
        return BIZ_EVENTQUEUE;
    }

    public static String getFollowerKey(int entityType, int entityId){
        return BIZ_FOLLOWER + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String  getFolloweeKey(int userId, int entityType){
        return BIZ_FOLLOWEE + SPLIT + String.valueOf(userId) + SPLIT + String.valueOf(entityType);
    }

    public static String getVarifyKey(String email){
        return BIZ_VARIFY + SPLIT + email;
    }
}

