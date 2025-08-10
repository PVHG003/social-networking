import { zodResolver } from "@hookform/resolvers/zod";
import type { FunctionComponent } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { z } from "zod";
import { Button } from "../ui/button";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "../ui/form";
import { Input } from "../ui/input";

interface AuthFormProps {
  type: "login" | "register";
}

const loginSchema = z.object({
  email: z.email("Invalid email address"),
  password: z.string().min(8, "Password must be at least 8 characters long"),
});

const registerSchema = z
  .object({
    email: z.email("Invalid email address"),
    password: z.string().min(8, "Password must be at least 8 characters long"),
    confirmPassword: z
      .string()
      .min(8, "Confirm password must be at least 8 characters long"),
  })
  .refine((data) => data.password === data.confirmPassword, {
    message: "Passwords must match",
    path: ["confirmPassword"],
  });

type LoginFormValues = z.infer<typeof loginSchema>;
type RegisterFormValues = z.infer<typeof registerSchema>;

const AuthForm: FunctionComponent<AuthFormProps> = ({ type }) => {
  const navigate = useNavigate();

  const schema = type === "login" ? loginSchema : registerSchema;

  const form = useForm<LoginFormValues | RegisterFormValues>({
    resolver: zodResolver(schema),
    defaultValues:
      type === "login"
        ? { email: "", password: "" }
        : { email: "", password: "", confirmPassword: "" },
  });

  const handleLogin = (values: LoginFormValues) => {
    console.log("Form submitted:", values);
    navigate("/");
  };

  const handleRegister = (values: LoginFormValues) => {
    console.log("Form submitted:", values);
    navigate("/");
  };

  return (
    <Form {...form}>
      <form
        onSubmit={
          type === "login"
            ? form.handleSubmit(handleLogin)
            : form.handleSubmit(handleRegister)
        }
        className="space-y-4"
      >
        {/* Email */}
        <FormField
          control={form.control}
          name="email"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Email</FormLabel>
              <FormControl>
                <Input {...field} type="email" placeholder="you@example.com" />
              </FormControl>
              <FormDescription>Your account email address</FormDescription>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* Password */}
        <FormField
          control={form.control}
          name="password"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Password</FormLabel>
              <FormControl>
                <Input {...field} type="password" placeholder="********" />
              </FormControl>
              <FormDescription>At least 8 characters</FormDescription>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* Confirm Password (Register only) */}
        {type === "register" && (
          <FormField
            control={form.control}
            name="confirmPassword"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Confirm Password</FormLabel>
                <FormControl>
                  <Input {...field} type="password" placeholder="********" />
                </FormControl>
                <FormDescription>Re-enter your password</FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />
        )}

        <Button type="submit" className="w-full">
          {type === "login" ? "Login" : "Register"}
        </Button>

        <div className="text-sm">
          {type === "login" ? (
            <span>
              Don't have an account?{" "}
              <Link to={"/register"} className="text-blue-500 hover:underline">
                Sign up
              </Link>
            </span>
          ) : (
            <span>
              Already have an account?{" "}
              <Link to={"/login"} className="text-blue-500 hover:underline">
                Sign In
              </Link>
            </span>
          )}
        </div>
      </form>
    </Form>
  );
};

export default AuthForm;
