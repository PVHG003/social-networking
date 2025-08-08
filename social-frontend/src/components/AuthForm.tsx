import { useState } from "react";
import { Input } from "./ui/input";
import { Label } from "./ui/label";
import { Button } from "./ui/button";
import { Link } from "react-router-dom";

interface AuthFormProps {
  type: "login" | "register";
  onSubmit: (data: {
    email: string;
    password: string;
    confirmPassword?: string;
  }) => void;
}

// Reusable AuthForm component for login and registration
const AuthForm = ({ type, onSubmit }: AuthFormProps) => {
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    const formData = new FormData(e.currentTarget);
    const data = {
      email: formData.get("email") as string,
      password: formData.get("password") as string,
      ...(type === "register" && {
        confirmPassword: formData.get("confirmPassword") as string,
      }),
    };
    onSubmit(data);
    setIsLoading(false);
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div className="space-y-2">
        <Label htmlFor="email">Email</Label>
        <Input
          id="email"
          name="email"
          type="email"
          placeholder="Enter your email"
          required
        />
      </div>
      <div className="space-y-2">
        <Label htmlFor="password">Password</Label>
        <Input
          id="password"
          name="password"
          type="password"
          placeholder="Enter your password"
          required
        />
      </div>
      {type === "register" && (
        <div className="space-y-2">
          <Label htmlFor="confirmPassword">Confirm Password</Label>
          <Input
            id="confirmPassword"
            name="confirmPassword"
            type="password"
            placeholder="Confirm password"
            required
          />
        </div>
      )}
      <Button type="submit" className="w-full" disabled={isLoading}>
        {isLoading ? "Submitting..." : type === "login" ? "Log In" : "Register"}
      </Button>
      {type === 'login' ? (
        <Link to={"/register"}>Create an account</Link>
      ) : (
        <Link to={"/login"}>Sign in</Link>
      )}
    </form>
  );
};

export default AuthForm;
