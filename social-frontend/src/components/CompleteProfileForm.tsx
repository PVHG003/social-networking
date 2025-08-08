import { useState } from "react";
import { Input } from "./ui/input";
import { Button } from "./ui/button";
import { Label } from "./ui/label";

interface CompleteProfileFormProps {
  onSubmit: (data: { username: string; bio?: string }) => void;
}

// CompleteProfileForm component
const CompleteProfileForm = ({ onSubmit }: CompleteProfileFormProps) => {
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    const formData = new FormData(e.currentTarget);
    const data = {
      username: formData.get("username") as string,
      bio: formData.get("bio") as string,
    };
    onSubmit(data);
    setIsLoading(false);
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div className="space-y-2">
        <Label htmlFor="username">Username</Label>
        <Input
          id="username"
          name="username"
          type="text"
          placeholder="Choose a username"
          required
        />
      </div>
      <div className="space-y-2">
        <Label htmlFor="bio">Bio</Label>
        <Input
          id="bio"
          name="bio"
          type="text"
          placeholder="Tell us about yourself"
        />
      </div>
      <Button type="submit" className="w-full" disabled={isLoading}>
        {isLoading ? "Saving..." : "Save Profile"}
      </Button>
    </form>
  );
};

export default CompleteProfileForm;
