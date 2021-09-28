import React, { Fragment, useState, useEffect } from "react";
import axios from "axios";

const Cryptoassets = () => {


    const [cryptoassets, setCryptoassets] = useState([]);

  useEffect(() => {
    getCryptoassets();
  }, []);

  const getCryptoassets = () => {
    //TODO: Hacer variables de entorno para manejar los llamados a la api
    axios
      .get("http://localhost:8080/api/cryptoassets")
      .then((result) => {
        setCryptoassets(result.data.data);
      })
      .catch(console.log);
  };

    return (
        <>
        <h2>Esto es el listado de crypto activos</h2>
           
        </>
    )
}

export default Cryptoassets;