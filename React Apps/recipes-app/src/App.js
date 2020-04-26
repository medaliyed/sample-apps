import React, { useEffect, useState } from 'react';
//import logo from './logo.svg';
import './App.css';
import Recipe from './Recipe';

function App() {
  const APP_ID = "fc5cd684";
  const APP_KEY = "286a4fcb436511adb72ec6827982d5de";

  const [recipes, setRecipes] = useState([]);
  const [search, setSearch] = useState("");
  const [query, setQuery] = useState("chiken");

  useEffect(() => {
    getRecipes();
  }, [query]);


  const getRecipes = async () => {
    const response = await fetch(`https://api.edamam.com/search?q=${query}&app_id=${APP_ID}&app_key=${APP_KEY}`);
    const data = await response.json();
    setRecipes(data.hits);
    console.log(data.hits);
    
  };
  const updateSearch = e => {
    setSearch(e.target.value);
    console.log(search);

  }
  const getSearch = e => {
    e.preventDefault();
    setQuery(search);
    setSearch('');

  }
  /*useEffect(()=>{
    getRecipes();
    console.log("effect");
    
  });**/
  return (
    <div className="App">
      <h1>Hello recipe app</h1>
      <form onSubmit={getSearch} className="search-form">
        <input className="search-bar" type="text" value={search} onChange={updateSearch} />
        <button className="search-button" type="submit">Search</button>
      </form>
      <div className="recipes">
      {
        recipes.map(recipe => (
          <Recipe
            key={recipe.recipe.label}
            title={recipe.recipe.label} 
            calories={recipe.recipe.calories} 
            image={recipe.recipe.image}
            ingredients={recipe.recipe.ingredients}
            />
        ))};
        </div>
    </div>
  );
}

export default App;
