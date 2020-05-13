$(document).ready(function() {
    $("a.addOption").on("click", addRow);
});

function addRow() {
    let count = $("div.options > div").length;
    let content = "<div>";
    content += "<input id='options"+count+".text' name='options[" + count + "].text' type='text' />";
    content += "<input id='options"+count+".value' name='options[" + count + "].value' type='number' />";
    content += "</div>";
    $("div.options").append(content);                
}