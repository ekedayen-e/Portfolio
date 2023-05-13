var currentQuote = '',
currentAuthor = '';
const colors = [
"red",
"blue",
"green",
"orange",
"yellow",
"pink",
"purple",
"aqua",
"cyan",
"coral"];

const quotes = [
{
  quote: "Your time is limited, so don’t waste it living someone else’s life.",
  author: "Steve Jobs" },

{
  quote: "If the wind will not serve, take to the oars.",
  author: "Latin Proverb" },

{
  quote: "I have learned over the years that when one’s mind is made up, this diminishes fear.",
  author: "Rosa Parks" },

{
  quote: "If you want your children to turn out well, spend twice as much time with them, and half as much money.",
  author: "Abigail Van Buren" },

{
  quote: "愿逝者有那不朽的声名，愿生者有那不朽的爱情",
  author: "Tagore" }];



function getRandomQuote() {
  return quotes[Math.floor(Math.random() * quotes.length)];
}

function getQuote() {
  let randomQuote = getRandomQuote();

  currentQuote = randomQuote.quote;
  currentAuthor = randomQuote.author;

  var val = Math.floor(Math.random() * colors.length);
  $('body').css(
  {
    "background-color": colors[val],
    "color": colors[val] });



  $('#text').text(randomQuote.quote);
  $('#author').text(randomQuote.author);

  $("#tweet-quote").attr('href', 'https://twitter.com/intent/tweet' + encodeURIComponent('"' + currentQuote + '" ' + currentAuthor));
}


$(document).ready(function () {
  $('#text').text(getQuote);
  $("#new-quote").on("click", getQuote);
});