import React, { useState, useEffect } from "react";
import PetList from "../components/PetList";
import Grid from "@material-ui/core/Grid";
import getAllPets from "../api/petRequests";
import { Button } from "@material-ui/core";
import AddRoundedIcon from "@material-ui/icons/AddRounded";
import { Link } from "react-router-dom";

const EmployeeDashboard = () => {

  

  const url = "http://localhost:8080/pet";

  const [petList, setPetList] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getAllPets(url)
      .then(petList => setPetList(petList))
      .catch(error => console.log(error))
      .finally(() => setLoading(false));
  }, []);

  const updatePetList = (id) => {
  setPetList({
    ...petList.filter(el => el != id)
  });
  }

  return (
    <div data-testid="empdash">
      <h1 align="center">Employee Dashboard</h1>
      {loading ? (
        <div data-testid="loading">Loading</div>
      ) : (
        <div data-testid="loadedList">
          <Grid container spacing={3}>
            <Grid item xs={12} sm>
              <Link to="/pet-register">
                <Button
                  variant="contained"
                  color="primary"
                  startIcon={<AddRoundedIcon />}
                >
                  Add Pet
                </Button>
              </Link>
            </Grid>
          </Grid>
          <Grid container spacing={3}>
            <Grid item xs={12} sm>
              <PetList deletePet={updatePetList} heading="Requested for Adoption" petList={petList} />
            </Grid>
            <Grid item xs={12} sm>
              <PetList heading="Adoptable Animals" petList={petList} />
            </Grid>
          </Grid>
        </div>
      )}
    </div>
  );
};

export default EmployeeDashboard;
