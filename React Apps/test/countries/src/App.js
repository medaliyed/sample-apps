import React, { useEffect, useState } from 'react';
import './App.css';
import Country from './Country';

function App() {

  const [countries, setCountries] = useState([]);
  const [search, setSearch] = useState("");
  const [query, setQuery] = useState("");

  useEffect(() => {
    if(query === ""){
      getCountries();
    }else{
      getCountryByFullName();
    };
    
  }, [query]);



/*question 3*/
  const getCountries = async () => {
    const response = await fetch("https://restcountries.eu/rest/v2/all");
    const data = await response.json();
    setCountries(data);
    console.log(data);
    
  };

  /*question 1 */
  const getCountryByFullName = async () => {
    const response = await fetch(`https://restcountries.eu/rest/v2/name/${query}?fullText=true`);
    const data = await response.json();
    setCountries(data);
    console.log(data);
    
  };

  /**question 2 */
  const getCountriesByString = async () => {
    var words = query.split(',');
    //console.log(words);
    let dataTotal ={};
    for(let i=0;i<words.length;i++){
     
    const response = await fetch(`https://restcountries.eu/rest/v2/name/${words[i]}`);
    const data = await response.json();
    dataTotal.concat(data);
    
    }
    setCountries(dataTotal);
    
    
  };

  //getCountriesByString();

  const updateSearch = e => {
    setSearch(e.target.value);
    console.log(search);

  }
  const getSearch = e => {
    e.preventDefault();
    setQuery(search);
    setSearch('');

  }
  return (
    <div className="App">
      <h1>Coutries searching app</h1>
      <form onSubmit={getSearch} className="search-form">
        <input className="search-bar" type="text" value={search} onChange={updateSearch} />
        <button className="search-button" type="submit">Search</button>
      </form>
      <div className="recipes">
    {countries.map(country => (
      <Country name = {country.name}
      capital = {country.capital}
      flag = {country.flag}
      />
    ))}
        </div>
    </div>
  );
}

export default App;
