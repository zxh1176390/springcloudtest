package ${package.ServiceImpl};

import ${package.Service}.${table.serviceName};
import ${package.Repository}.${entity}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* <p>
* ${table.comment!} 服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.entityName}ServiceImpl implements ${table.serviceName} {
    @Autowired
    private ${table.entityName}Repository ${table.entityName?uncap_first}Repository;

}