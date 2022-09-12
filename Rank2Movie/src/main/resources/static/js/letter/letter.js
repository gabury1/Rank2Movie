let sock = new SockJS("/letter");
sock.onopen = () => console.log("Info : letter connected");
sock.onmessage = (message) =>{

    var data = JSON.parse(message.data);
    if(data.for == "userCnt")
    {
        $("#lblUserCnt").html("현재 연결된 유저 : " + data.userCnt);
    }

};

sock.onclose = () => console.log("Info : letter disconnected");


function letterSend()
{
    var letter = {
        "purpose" : "letter",
        "title" : $("#sendTitle").val(),
        "Movie" : $("sendMovie").val(),
        "content" : $("#sendContent").val(),
        "howMany" : $("#sendHowMany").val(),
    };
    
    console.log(JSON.stringify(letter))
    sock.send(JSON.stringify(letter));
}




