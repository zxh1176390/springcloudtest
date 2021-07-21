package ${package.RepositoryImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Repository}.${table.entityName}Repository;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Repository;

/**
* <p>
* ${table.comment!} Repository服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Repository
<#if kotlin>
open class ${table.entityName}RepositoryImpl : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.entityName}Repository {

}
<#else>
public class ${table.entityName}RepositoryImpl extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.entityName}Repository {

}
</#if>
