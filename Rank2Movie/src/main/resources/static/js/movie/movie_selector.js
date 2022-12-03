var page = 0;
var str = "";
var selected = "";

var movies = "";

function movieSelector(data)
{
    // css 스타일시트를 추가해주는 코드
    // :hover를 이렇게 구현해야했다..
    var css = `
        .item:hover{ background-color: #f5f5dc; cursor: pointer;}
        .pointer:hover{cursor: pointer;}
    `;
    var style = document.createElement('style');

    if (style.styleSheet) {
        style.styleSheet.cssText = css;
    } else {
        style.appendChild(document.createTextNode(css));
    }

    document.getElementsByTagName('head')[0].appendChild(style);

    data.element.innerHTML = `        
    <div id="movieSelector" style="border : 1px solid; display: inline-block;">
    <input id="search" style="height: 40px; width:90%;" placeholder="제목이나 영화 코드를 입력해주세요.">
    <button id="searchStart" onclick="changeStr()">검색</button><br>
    <table id="resultBox" style="width: ${data.width}; height: ${data.height};">
        <tr style="height: 90%;">
            <td id="listBox" style="width: 65%; border: 1px solid;" valign="top">

            </td>
            <td id="imageBox" style="border: 1px solid;" rowspan="2" align="center" valign="top">
                <img id="poster" style="width:160px; height:220px; margin:5px;"  src="https://movie-phinf.pstatic.net/20220809_177/1660033655042WSHnk_JPEG/movie_image.jpg?type=m203_290_2">
                <label id="name">영화명</label>
            </td>
        </tr>
        <tr>
            <td id="pageBox" style="padding: 0px 0px 0px 10px;" align="center">
                
            </td>
        </tr>
    </table>
    
    </div>`;
}

function search()
{
    $.ajax({
        url : "/movie/movie_selector",
        data : {str : str, page : page},
        method : "GET",
        success : (data) => {
            console.log(data);
            var movieHtml = "";

            movies = data.movies;
            for(var i=0; i < data.movies.length; i++)
            {
                movieHtml += `
                <div class="item" onclick="selectElement(${i})" style="height: 20%; line-height:50px; margin-left:10px; border:1px solid;">
                    <label class="pointer" style="margin: 0px 0px 0px 10px;">${data.movies[i].titleKor}</label>
                </div>
                `;
            }

            $("#listBox").html(movieHtml);

            var pageHtml = "";
            var now = data.pageNow;
            var total = data.pageTotal;

            if(0 < now)
            pageHtml += `
                <label class="pointer" onclick="changePage(${now-1})"> < </label>
            `;
            pageHtml += (now+1);

            if(now+1 < total)
            pageHtml += `
            <label class="pointer" onclick="changePage(${now+1})"> > </label>
            `;


            $("#pageBox").html(pageHtml);
            selectElement(0);

        },
        error : (e) => alert(e.responsText)
    });

}

async function changeStr()
{
    str = $("#search").val();
    page = 0;
    search();
}

function changePage(num)
{
    page = num;
    search();
}

function selectElement(index)
{
    selected = movies[index].movieCode; 
    $("#poster").attr("src", movies[index].imageUrl);
    $("#name").text(movies[index].titleKor);

}