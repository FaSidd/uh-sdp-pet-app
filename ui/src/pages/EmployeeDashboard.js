import React, { useState, useEffect } from "react";
import PetList from "../components/PetList";
import Grid from "@material-ui/core/Grid";
import Navigation from "../components/Navigation";

const EmployeeDashboard = () => {
  const [petList, setPetList] = useState([]);

  async function fetchData() {
    const res = await fetch("http://localhost:8080/pet"); // fetch("https://swapi.co/api/planets/4/");
    res.json().then(res => setPetList(res));
  }

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div data-testid="empdash">
      <h1 align="center">This is the Employee Dashboard Page</h1>
      <div>
        <Grid container spacing={3}>
          <Grid item xs={12} sm>
            <PetList heading="Pet List" petList={petList} />
          </Grid>
          <Grid item xs={12} sm>
            <PetList heading="Pet List 2" petList={petList} />
          </Grid>
          <Grid item xs={12} sm>
            <PetList heading="Pet List 3" petList={petList} />
          </Grid>
        </Grid>
      </div>
    </div>
  );
};

export default EmployeeDashboard;
