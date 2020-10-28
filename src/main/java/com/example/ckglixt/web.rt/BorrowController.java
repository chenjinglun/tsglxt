package com.example.ckglixt.web.rt;

import com.example.ckglixt.requestDTO.BorrowDTO;
import com.example.ckglixt.requestDTO.ListUsersRequestDTO;
import com.example.ckglixt.service.impl.BorrowServicelmpl;
import com.example.ckglixt.utils.ResponceData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 图书借阅
 * @author xukunyuan
 * @date 2020-10-20 14:55
 */
@RestController
@RequestMapping("/Api")
public class BorrowController {
    private static final Logger logger = LoggerFactory.getLogger(BorrowController.class);

    @Resource
    private BorrowServicelmpl borrowService;

    /**
    * @Description: 图书借阅
    * @Param:  borrowDTO
    * @return:  Response
    * @Author: xukunyuan
    * @Date: 2020/10/20
    */
    @PostMapping("borrowBook")
    @ApiOperation(value = "图书借阅",notes = "没有返回值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "isbn",value = "图书编号", required =true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "bookCnt",value = "借阅数量", required =true,dataType = "String")
    })
    private ResponceData borrowBook(BorrowDTO borrowDTO){
        try{
            return borrowService.borrowBook(borrowDTO);
        }catch (Exception e){
            logger.error("借阅失败，请重试",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
    * @Description: 图书归还
    * @Param:  returnBook
    * @return:  Response
    * @Author: xukunyuan
    * @Date: 2020/10/20
    */
    @PostMapping("returnBook")
    @ApiOperation(value = "图书归还",notes = "没有返回值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "borrowId",value = "借阅编号", required =true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "bookCnt",value = "借阅数量", required =true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "isbn",value = "图书编号", required =true,dataType = "String")
    })
    public ResponceData returnBook(BorrowDTO borrowDTO){
        try{
            return borrowService.returnBook(borrowDTO);
        }catch (Exception e){
            logger.error("归还失败，请重试",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /** 
    * @Description: 查询借阅信息 
    * @Param:  getBorrowDetail
    * @return:  Response
    * @Author: xukunyuan
    * @Date: 2020/10/20 
    */
    @PostMapping("getBorrowDetail")
    @ApiOperation(value="查询借阅详情", notes="返回借阅信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "borrowId", value = "借阅id", required = true, dataType = "String"),
    })
    public ResponceData getBorrowDetail(String borrowId){
        try{
            return borrowService.getBorrowDetail(borrowId);
        }catch (Exception e){
            logger.error("查询失败，请重试");
            System.out.println(e.toString());
            throw e;
        }
    }
    /** 
    * @Description: 列表查询借阅信息
    * @Param:  listBorrow
    * @return:  response
    * @Author: xukunyuan
    * @Date: 2020/10/20 
    */
    @PostMapping(value = "listBorrow")
    @ApiOperation(value="借阅信息列表分页查询", notes="返回借阅信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户编号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userName", value = "借阅人", required = false, dataType = "String")
    })
    private ResponceData listBorrow(BorrowDTO borrowDTO){
        try{
            return borrowService.listBorrow(borrowDTO);
        }catch (Exception e){
            logger.error("查询失败，请重试");
            System.out.println(e.toString());
            throw e;
        }
    }

}
