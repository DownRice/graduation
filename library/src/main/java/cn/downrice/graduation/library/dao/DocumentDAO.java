package cn.downrice.graduation.library.dao;

import cn.downrice.graduation.library.model.library.Document;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DocumentDAO {
    String TABLE_NAME = " document ";
    String INSERT_FIELDS = " real_id, title, uploader, upload_time, author, type, loc, is_open ";
    String SELECT_FILEDS = " id," + INSERT_FIELDS;

    @Insert({"INSERT INTO ", TABLE_NAME, "(", INSERT_FIELDS, ") VALUES (#{realId}, #{title}, #{uploader}, #{uploadTime}, #{author}, #{type}, #{loc}, #{isOpen})"})
    int insertDocument(Document document);

    //用户体验相关：调用这个的，在服务那里先去掉首尾书名号
    @Select({"SELECT ", SELECT_FILEDS, " FROM ", TABLE_NAME, " WHERE title like '#{title}%'"})
    List<Document> getDocumentsByTitle(String title);

    @Select({"SELECT ", SELECT_FILEDS, " FROM ", TABLE_NAME, " WHERE real_id = #{realId}"})
    Document getDocumentByRealId(String realId);

    @Delete({"DELETE FROM ", TABLE_NAME, " WHERE real_id = #{realId}" })
    int deleteDocumentByRealId(String realId);

    @Update({"UPDATE ", TABLE_NAME, " SET title=#{title}, upload_time=#{uploadTime}, author=#{author}, type=#{type}, is_open=#{isOpen} WHERE real_id=#{realId}"})
    int updateDocument(Document document);

}
