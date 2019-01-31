package tycoding.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.cloud.client.model.SeckillModel;
import spring.cloud.seckill.dataaccess.mapper.SeckillMapper;

import java.util.Date;
import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application.yml")
@SpringBootTest
public class SeckillMapperTest {

    @Autowired
    private SeckillMapper seckillMapper;

    @Test
    public void findAll() {
        List<SeckillModel> all = seckillMapper.findAll();
        for (SeckillModel seckill : all) {
            System.out.println(seckill.getTitle());
        }
    }

    @Test
    public void findById() {
        SeckillModel seckill = seckillMapper.findById(1l);
        System.out.println(seckill.getTitle());
    }

    @Test
    public void reduceStock() {
        int row = seckillMapper.reduceStock(1l, new Date());
        System.out.println(row);
    }
}