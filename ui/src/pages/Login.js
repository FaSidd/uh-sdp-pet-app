import React from "react";
import LoginForm from "../components/LoginForm";
import { makeStyles } from "@material-ui/core/styles";

const Login = () => {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <LoginForm />
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
  }
});

export default Login;
