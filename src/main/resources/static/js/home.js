var numSelected = null;
var tileSelected = null;
var errors = 0;
let gameWon = false;

var board = [
    "2---",
    "-1-2",
    "--3-",
    "---4"
]

var solution = [
    "2413",
    "3142",
    "4231",
    "1324"
]

window.onload = function() {
    setGame();
}








// Board and Digits
function setGame(){

    // Digits 1 - 4
    for(let i = 1; i <= 4; i++){
        //<div> </div>
        let number = document.createElement("div");
        number.id = i;
        number.innerText = i;
        number.addEventListener("click", selectNumber)
        number.classList.add("number");
        document.getElementById("digits").appendChild(number);
    }

    // Board 4x4
    for(let r = 0; r < 4; r++){
        for(let c = 0; c < 4; c++){
            let tile = document.createElement("div");
            tile.id = r.toString() + "-" + c.toString();
            if(board[r][c] != "-"){
                tile.innerText = board[r][c];
                tile.classList.add("starter-tiles");
            }
            if (r == 1){
                tile.classList.add("hor-line");
            }
            if (c == 1){
                tile.classList.add("ver-line");
            }
            tile.addEventListener("click", selectTile);
            tile.classList.add("tile");
            document.getElementById("board").append(tile);
        }
    }
}







// If a number is selected
function selectNumber(){
    if (numSelected != null){
        numSelected.classList.remove("number-selected");
    }
    numSelected = this;
    numSelected.classList.add("number-selected");
}


// Logic for if tile is selected and it is empty place the number
function selectTile(){
    if (numSelected){
        if(this.innerText != ""){
            return;
        }

    }

    let coords = this.id.split("-");
    let r = parseInt(coords[0]);
    let c = parseInt(coords[1]);

    if(solution[r][c] == numSelected.id){
        this.innerText = numSelected.id;
    }
    else{
        errors += 1;
        document.getElementById("errors").innerText = errors;
    }

    gameIsWonOrLost();
}


// Game is Won
function gameIsWonOrLost() {
    let tiles = document.querySelectorAll('.tile:not(.starter-tiles)');
    let allCorrect = true;

    tiles.forEach(tile => {
        let coords = tile.id.split("-");
        let r = parseInt(coords[0]);
        let c = parseInt(coords[1]);

        if (tile.innerText !== solution[r][c]) {
            allCorrect = false;
            return;
        }
    });

    if (allCorrect) {
        gameWon = true;
        displayMessageIfWon();
    } else{
        gameIsLost();
    }



}

// If game is lost, display this message
function displayMessageIfLost() {
    if(errors >= 3) {
        document.getElementById("loseMessage").style.display = 'block';
    }
    setTimeout(function() {
            document.getElementById("loseMessage").style.display = "none";}
        , 1500);
}



// If game is won, display this message
function displayMessageIfWon(){
    var errorAmount = document.getElementById("errors").innerText;
    document.getElementById("winMessage").innerHTML += "<h3>It only took " + errorAmount + " misses</h3>";
    document.getElementById("winMessage").style.display = 'block';

    setTimeout(function() {
            document.getElementById("winMessage").style.display = "none";}
        , 1500);
}


// Game is lost, reset game and tell them they lost the game
function gameIsLost(){
    if(errors >= 3){
        displayMessageIfLost();
        resetGame();
    }
}



// Reset the game
function resetGame(){

    // Setting all the numbers on the bottom row to nothing
    let digitsContainer = document.getElementById("digits");
    digitsContainer.innerHTML = '';

    // Setting all of the board html to nothing
    let boardContainer = document.getElementById("board");
    boardContainer.innerHTML = '';

    // Setting the errors to 0 when game is over
    errors = 0;
    document.getElementById("errors").innerText = errors;


    // calling the game
    setGame();
}

// Reset Button, Resets the game
function resetGameButton(){
    const resetButton = document.getElementById("resetButton");
    resetButton.addEventListener("click", resetGame());

}