import React, { useState, useEffect } from 'react';
import Grid from '@material-ui/core/Grid';
import { Button, CircularProgress, makeStyles } from '@material-ui/core';
import AddRoundedIcon from '@material-ui/icons/AddRounded';
import { Link } from 'react-router-dom';
import { getAllPets } from '../api/petRequests';
import PetList from '../components/PetList';

const useStyles = makeStyles({
  addButton: {
    textDecorationLine: 'none',
  },
  progress: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

const EmployeeDashboard = () => {
  const url = 'http://localhost:8080/pet';

  const [petList, setPetList] = useState([]);
  const [loading, setLoading] = useState(true);

  const classes = useStyles();

  useEffect(() => {
    getAllPets(url)
      // eslint-disable-next-line no-shadow
      .then((petList) => setPetList(petList))
      .catch((error) => {
        throw error;
      })
      .finally(() => setLoading(false));
  }, []);

  const removePetFromList = (id) => {
    setPetList(petList.filter((el) => el.id !== id));
  };

  if (localStorage.getItem('roles') === 'ROLE_Employee') {
    return (
      <div data-testid="empdash">
        <h1 align="center">Employee Dashboard</h1>
        {loading ? (
          <div data-testid="loading" className={classes.progress}>
            <CircularProgress color="secondary" />
          </div>
        ) : (
          <div align="center" data-testid="loadedList">
            <Grid container>
              <Grid item xs={12} sm>
                <Link to="/pet-register" className={classes.addButton}>
                  <Button
                    variant="contained"
                    color="primary"
                    startIcon={<AddRoundedIcon />}
                    className={classes.addButton}
                  >
                    Add Pet
                  </Button>
                </Link>
              </Grid>
            </Grid>
            <Grid container>
              <Grid item xs={12} sm>
                <PetList
                  deletePet={removePetFromList}
                  heading="Requested for Adoption"
                  petList={petList}
                />
              </Grid>
              <Grid item xs={12} sm>
                <PetList heading="Adoptable Animals" petList={petList} />
              </Grid>
            </Grid>
          </div>
        )}
      </div>
    );
  } else if (localStorage.getItem('roles') === 'ROLE_User') {
    window.location.replace('http://localhost:3000/user-dashboard');
  } else {
    window.location.replace('http://localhost:8080/login');
  }
};

export default EmployeeDashboard;
