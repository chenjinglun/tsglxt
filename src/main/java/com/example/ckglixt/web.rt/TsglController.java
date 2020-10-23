package com.example.ckglixt.web.rt;

import com.example.ckglixt.requestDTO.*;
import com.example.ckglixt.service.impl.TsglService;
import com.example.ckglixt.utils.ResponceData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/tsgl")
public class TsglController {
    private static final Logger logger = LoggerFactory.getLogger(TsglController.class);
    @Autowired
    private TsglService tsglService;
    /**
     * 图书采购
     */
    @PostMapping("/addTs")
    @ApiOperation(value="图书采购", notes="没有返回值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "bookName", value = "采购书名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "createTime", value = "采购时间", required = false, dataType = "date"),
            @ApiImplicitParam(paramType="query", name = "num", value = "采购数量", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "bookInType", value = "采购类型,1:购买，2：捐赠，3：回收", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "price", value = "价格", required = true, dataType = "String")
    })
    public ResponceData addTs(TsglAddEntity tsglAddEntity){
        try {
            return tsglService.addTs(tsglAddEntity);
        }catch (Exception e){
            logger.error("图书采购失败!",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 图书出库
     */
    @PostMapping("/deleteTsFromCk")
    @ApiOperation(value="图书出库", notes="无返回")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "用逗号一一对应分开id", required = true, dataType = "String")
    })
    public ResponceData deleteTsFromCk(TsglDeleteEntity tsglDeleteEntity){
        try {
            return tsglService.deleteTsFromCk(tsglDeleteEntity);
        }catch (Exception e){
            logger.error("图书出库失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询未入库图书
     */
    @PostMapping("/ListOfTs")
    @ApiOperation(value="查询未入库图书", notes="返回未入库图书分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页数", required = true, dataType = "int")
    })
    public ResponceData ListOfTs(ListUsersRequestDTO listUsersRequestDTO){
        try {
            return tsglService.ListOfTs(listUsersRequestDTO);
        }catch (Exception e){
            logger.error("查询图书分页列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 采购入库操作
     */
    @PostMapping("/addCk")
    @ApiOperation(value="采购入库操作", notes="无返回信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "采购id,全部对应用逗号分开，下面参数也是！", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "bookName", value = "采购书名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "num", value = "采购数量", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType="query", name = "bookInType", value = "采购类型,1:购买，2：捐赠，3：回收", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "price", value = "价格", required = true, dataType = "String")
    })
    public ResponceData addCk(AddCkRequestDTO addCkRequestDTO){
        try {
            return tsglService.addCk(addCkRequestDTO);
        }catch (Exception e){
            logger.error("入库失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 分页查询入库图书
     */
    @PostMapping("/ListOfRkTs")
    @ApiOperation(value="分页查询入库图书", notes="返回入库图书分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页数", required = true, dataType = "int")
    })
    public ResponceData ListOfRkTs(ListUsersRequestDTO listUsersRequestDTO){
        try {
            return tsglService.ListOfRkTs(listUsersRequestDTO);
        }catch (Exception e){
            logger.error("分页查询入库图书列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改图书
     */
    @PostMapping("/updateTs")
    @ApiOperation(value="修改图书", notes="没有返回值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "id,需要传过来，但是不显示不修改", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "bookAuthor", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "price", value = "价格", required = true, dataType = "float"),
            @ApiImplicitParam(paramType="query", name = "bookPress", value = "出版社", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "num", value = "数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "detail", value = "书籍介绍", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "bookPlace", value = "书本存放地方", required = true, dataType = "String")
    })
    public ResponceData updateTs(UpdateTsRequestDTO updateTsRequestDTO){
        try {
            return tsglService.updateTs(updateTsRequestDTO);
        }catch (Exception e){
            logger.error("修改图书失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
