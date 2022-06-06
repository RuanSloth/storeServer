package com.rwh.dao;
/**
 *
 */


import com.rwh.pojo.Evaluation;
import com.rwh.utils.JdbcUtils;
import com.rwh.utils.imageUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 * @author Administrator 测试写入数据库以及从数据库中读取
 */
public class ImageDao {
    /**
     *   将用户图片插入数据库
     * @param username
     * @param path 图片路径
     * @return -1失败，1成功
     */
    public static int userImage(String username,String path) {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(path);
            conn = JdbcUtils.getConnection();
            String sql = "update user set headphoto=? where username=?";
            ps = conn.prepareStatement(sql);

            ps.setString(2, username);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }

    /**
     * 读取用户图片
     * @param targetPath 生成临时文件的路径
     */
    public static void readUserImage(String username,String targetPath) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("headphoto");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath);
//                System.out.println("生成成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 修改商品封面图片
     * @param goodId
     * @param photo 图片路径
     * @return -1更新失败 1成功
     */
    public int updateCoverphoto(Integer goodId,String photo){
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(photo);
            conn = JdbcUtils.getConnection();
            String sql = "update good set coverphoto=? where id=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(2, goodId);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }


    /**
     * 修改商品详细图片1
     * @param goodId
     * @param photo
     * @return -1更新失败 1成功
     */
    public int updatePhotoOne(Integer goodId,String photo){
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(photo);
            conn = JdbcUtils.getConnection();
            String sql = "update good set detailone=? where id=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(2, goodId);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }

    /**
     * 修改商品详细图片2
     * @param goodId
     * @param photo
     * @return -1更新失败 1成功
     */
    public int updatePhotoTwo(Integer goodId,String photo)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(photo);
            conn = JdbcUtils.getConnection();
            String sql = "update good set detailtwo=? where id=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(2, goodId);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }
    /**
     * 修改商品详细图片3
     * @param goodId
     * @param photo
     * @return -1更新失败 1成功
     */
    public int updatePhotoThree(Integer goodId,String photo)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(photo);
            conn = JdbcUtils.getConnection();
            String sql = "update good set detailthree=? where id=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(2, goodId);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }



    /**
     * 读取商品封面图片
     * @param targetPath 生成临时文件的路径
     */
    public static void readGoodImage(Integer id,String targetPath) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from good where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("coverphoto");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath);
//               System.out.println("生成成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 读取商品详细图片
     * @param targetPath1 生成临时文件的路径
     */
    public static void readDetailImage(Integer id,String targetPath1,String targetPath2,String targetPath3) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select detailone,detailtwo,detailthree from good where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("detailone");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath1);
                in = rs.getBinaryStream("detailtwo");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath2);
                in = rs.getBinaryStream("detailthree");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath3);
//               System.out.println("生成成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 写入评价图片1
     * -1失败，1成功
     */
    public int updateEvaluationPhoto1(Integer eid,String photo){
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(photo);
            conn = JdbcUtils.getConnection();
            String sql = "update evaluation set photoone = ? where eid = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(2, eid);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }
    /**
     * 写入评价图片2
     */
    public int updateEvaluationPhoto2(Integer eid,String photo){
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(photo);
            conn = JdbcUtils.getConnection();
            String sql = "update evaluation set phototwo = ? where eid = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(2, eid);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }
    /**
     * 写入评价图片3
     */
    public int updateEvaluationPhoto3(Integer eid,String photo){
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        int count =- 1;
        try {

            in = imageUtil.readImage(photo);
            conn = JdbcUtils.getConnection();
            String sql = "update evaluation set photothree = ? where eid = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(2, eid);
            ps.setBinaryStream(1, in, in.available());
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return count;
    }
    /**
     * 读取评价的图片
     */
    public static void readEvaluationImage(Integer eid,String targetPath1,String targetPath2,String targetPath3) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select photoone,phototwo,photothree from evaluation where eid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, eid);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("photoone");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath1);
                in = rs.getBinaryStream("phototwo");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath2);
                in = rs.getBinaryStream("photothree");
                if(in != null)
                    imageUtil.readBin2Image(in, targetPath3);
//               System.out.println("生成成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}