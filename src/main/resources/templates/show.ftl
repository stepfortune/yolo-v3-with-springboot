<!DOCTYPE html>
<head>
    <meta charset="UTF-8"/>
    <title>detection demo</title>
</head>
<body>
<form action="fileUpload" method="post" enctype="multipart/form-data">
    <input type="file" name="fileName"/>
    <p>
        <input type="submit" value="提交"/>
         <#if msg??>
            <span>${msg}</span><br>
        <#else >
        <span>${msg!("文件未上传")}</span><br>
</#if>
    </p>
</form>

<#if fileName??>
<img src="/show?fileName=${fileName}"/>
</#if>
</body>
</html>
