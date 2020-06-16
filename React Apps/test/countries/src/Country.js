import React from 'react';
import style from './country.module.css';

const Country= ({ name, capital, flag }) => {
    return (<div className={style.country}>
        <h1>{name}</h1>
        {/* <ol>
            {ingredients.map(ingredient =>(
                <li>{ingredient.text}</li>
            ))}
        </ol> */}
        <h2>{capital}</h2>
        <img src={flag} className={style.img} alt="" />

    </div>
    );
};

export default Country;