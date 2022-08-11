function getDetail()
{
    $.ajax({
        url : "/user/",
        data : {"userNo" : getNo()},
        method : "GET",
        success : (data) =>
        {
            // 데이터가 없을 때
            if(data.length == 0)
            {
                location.href = "";
                return ; // 아예 날려버린다.
            }


            $("#name").val(data.userName);
            $("#id").val(data.userId);
            $("#pw").val(data.userPassword);
            $("#detail").text(data.userDetail);
            if(data.owner == "true")
            {
                $("#owner").text("본인이시군요 :)");
                $("#dangerBox").show();
            }
            else
            {
                $("#owner").text("방문자이신가요? :)");
                $("#dangerBox").hide();
            }

        },
        error : () => alert(e.responseText)
    })
}

function getNo()
 {
     return  document.location.href.split("/")[4];
 }
