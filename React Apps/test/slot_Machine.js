function spin(obj){
 let coins= obj.coins;
 if(coins <1){
   console.log("not enough coins");
   return null;
 }
const reel1 =  ["cherry", "lemon", "apple", "lemon", "banana", "banana", "lemon", "lemon"];
const reel2 = ["lemon", "apple", "lemon", "lemon", "cherry", "apple", "banana", "lemon"];
const reel3 = ["lemon", "apple", "lemon", "apple", "cherry", "lemon", "banana", "lemon"];

coins -=1;
const randomReel1 = reel1[Math.floor(Math.random() * reel1.length)];
const randomReel2 = reel2[Math.floor(Math.random() * reel2.length)];
const randomReel3 = reel3[Math.floor(Math.random() * reel3.length)];
const result = [randomReel1, randomReel2, randomReel3];
console.log("random spin =>", result);

//console.log("filtering =>", result.filter(fruit => fruit == "cherry").length == 2);
if(result.filter(fruit => fruit == "cherry").length == 3){
  console.log("you won");
    coins +=50;
}else if(result.filter(fruit => fruit == "cherry").length == 2){
  console.log("you won");
    coins +=40;
}else if(result.filter(fruit => fruit == "apple").length == 3){
  console.log("you won");
    coins +=20;
}else if(result.filter(fruit => fruit == "apple").length == 2){
  console.log("you won");
    coins +=10;
}else if(result.filter(fruit => fruit == "banana").length == 3){
  console.log("you won");
    coins +=15;
}else if(result.filter(fruit => fruit == "banana").length == 2){
  console.log("you won");
    coins +=5;
}else if(result.filter(fruit => fruit == "lemon").length == 3){
  console.log("you won");
    coins +=3;
}
console.log(coins)


  return coins;
}


var starter = { coins: 20 };
  for(let i =0;i<30;i++){
   
  starter.coins = spin(starter);
    
    
  }
