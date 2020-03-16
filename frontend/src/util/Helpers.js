function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

export function formatDateTime(dateTimeString) {
  const date = new Date(dateTimeString);

  const monthNames = [
    "Jan", "Feb", "Mar", "Apr",
    "May", "Jun", "Jul", "Aug", 
    "Sep", "Oct", "Nov", "Dec"
  ];

  const monthIndex = date.getMonth();
  const year = date.getFullYear();

  return (year + ". " + monthNames[monthIndex] + '. ' + date.getDate() + '. ' + addZero(date.getHours()) + ':' + addZero(date.getMinutes())).toString();
}  
