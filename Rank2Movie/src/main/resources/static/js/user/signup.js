var checked = false;

// 회원가입
function signup()
{
    if(checked == false)
    {
        alert("ID 체크를 해주세요.");
        return;
    }

    let id = $("#id").val();
    let pw = $("#pw").val();
    let repw = $("#repw").val();
    let name = $("#name").val();

    $.ajax({
        url : "/user/",
        data : {"id" : id, "pw" : pw, "repw" : repw, "name" : name},
        method : "POST",
        success : data => alert(data),
        error : e => alert(e.responseText)
    })
}

// ID 중복확인
function idCheck()
{
    var id = $("#id").val();

    $.ajax({
        url : "/user/idcheck",
        data : {"id" : id},
        method : "GET",
        success : message => {
            if(message == "success")
            {
                $("#id").attr("disabled", true);
                checked = true;
            }
        },
        error : e => alert(e.responseText)

    })

}