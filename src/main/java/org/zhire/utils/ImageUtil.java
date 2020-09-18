package org.zhire.utils;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

import java.awt.*;

public class ImageUtil {
    public static void main(String[] args) {
        ImgUtil.pressText(
                FileUtil.file("/Users/admin/Documents/1.jpg"), //
                FileUtil.file("/Users/admin/Documents/2.jpg"), //
                "CQ", Color.WHITE, //文字
                new Font("黑体", Font.BOLD, 100), //字体
                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
    }
}
