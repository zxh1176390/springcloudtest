package ${package.Repository};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* <p>
* ${table.comment!} 服务类
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
interface ${table.entityName}Repository : ${superServiceClass}<${entity}>
<#else>
public interface ${table.entityName}Repository extends ${superServiceClass}<${entity}> {

}
</#if>
