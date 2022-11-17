package top.oneyi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oneyi.mapper.MdCusMapper;
import top.oneyi.pojo.MdCus;
import top.oneyi.service.MdCusService;

import java.util.List;

@Service
public class MdCusServiceImpl implements MdCusService {

    @Autowired
    private MdCusMapper mdCusMapper;

    public List<MdCus> list(){
        return mdCusMapper.selectAll();
    }

}
