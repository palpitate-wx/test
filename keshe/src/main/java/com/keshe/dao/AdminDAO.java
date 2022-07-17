package com.keshe.dao;

import com.keshe.dataobject.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDAO {

    /**
     * 增加管理员
     * @param admin
     * @return
     */
    int addAdmin(Admin admin);

    /**
     * 根据工号删除管理员
     * @param adminId
     * @return
     */
    int deleteAdmin(@Param("adminId") long adminId);

    /**
     * 根据工号查询管理员
     * @param adminId
     * @return
     */
    Admin getAdmin(@Param("adminId") long adminId);

    /**
     * 修改密码
     * @param adminId
     * @param newPassword
     * @return
     */
    int updatePassword(@Param("adminId") long adminId,@Param("newPassword") String newPassword);


}
