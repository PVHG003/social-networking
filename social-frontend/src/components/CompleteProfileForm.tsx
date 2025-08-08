import { useEffect, useState } from "react";
import { Input } from "./ui/input";
import { Button } from "./ui/button";
import { Label } from "./ui/label";
import { RadioGroup, RadioGroupItem } from "./ui/radio-group";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";

interface CompleteProfileFormProps {
  onSubmit: (data: FormData) => void;
}

// CompleteProfileForm component
const CompleteProfileForm = ({ onSubmit }: CompleteProfileFormProps) => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [avatarPreview, setAvatarPreview] = useState<string | null>(null);
  const [avatarFile, setAvatarFile] = useState<File | null>(null);

  useEffect(() => {
    return () => {
      if (avatarPreview) {
        URL.revokeObjectURL(avatarPreview);
      }
    };
  }, [avatarPreview]);

  const handleAvatarChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      // Validate file type and size (e.g., max 5MB)
      if (!file.type.startsWith("image/")) {
        setError("Please select an image file.");
        setAvatarFile(null);
        setAvatarPreview(null);
        return;
      }
      if (file.size > 5 * 1024 * 1024) {
        setError("Image size must be less than 5MB.");
        setAvatarFile(null);
        setAvatarPreview(null);
        return;
      }
      setAvatarFile(file);
      setAvatarPreview(URL.createObjectURL(file));
      setError(null);
    }
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);

    if (!avatarFile) {
      setError("Please select an avatar image.");
      setIsLoading(false);
      return;
    }

    try {
      const formData = new FormData(e.currentTarget);
      const profileData = {
        displayName: formData.get("displayName") as string,
        bio: formData.get("bio") as string,
        location: formData.get("location") as string,
        websiteUrl: formData.get("websiteUrl") as string,
        birthday: formData.get("birthday") as string,
        gender: formData.get("gender") as "MALE" | "FEMALE" | "OTHER",
      };
      formData.append(
        "data",
        new Blob([JSON.stringify(profileData)], { type: "application/json" })
      );
      formData.append("file", avatarFile);

      onSubmit(formData);
    } catch (error) {
      console.error("Error submitting form:", error);
      setError("Failed to complete profile. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div className="space-y-2">
        <Label htmlFor="displayName">Display Name</Label>
        <Input
          id="displayName"
          name="displayName"
          type="text"
          placeholder="Enter your display name"
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
      <div className="space-y-2">
        <Label htmlFor="location">Location</Label>
        <Input
          id="location"
          name="location"
          type="text"
          placeholder="Where are you located?"
        />
      </div>
      <div className="space-y-2">
        <Label htmlFor="websiteUrl">Website</Label>
        <Input
          id="websiteUrl"
          name="websiteUrl"
          type="url"
          placeholder="Your website URL"
        />
      </div>
      <div className="space-y-2">
        <Label htmlFor="birthday">Birthday</Label>
        <Input
          id="birthday"
          name="birthday"
          type="date"
          placeholder="Your birthday"
        />
      </div>
      <div className="space-y-2">
        <Label htmlFor="gender">Gender</Label>
        <RadioGroup
          name="gender"
          required
          className="flex space-x-4"
          defaultValue="MALE"
        >
          <div className="flex items-center space-x-2">
            <RadioGroupItem value="MALE" id="male" />
            <Label htmlFor="male">Male</Label>
          </div>
          <div className="flex items-center space-x-2">
            <RadioGroupItem value="FEMALE" id="female" />
            <Label htmlFor="female">Female</Label>
          </div>
          <div className="flex items-center space-x-2">
            <RadioGroupItem value="OTHER" id="other" />
            <Label htmlFor="other">Other</Label>
          </div>
        </RadioGroup>
      </div>
      <div className="space-y-2">
        <Label htmlFor="avatar">Avatar</Label>
        <Avatar className="w-24 h-24">
          <AvatarImage src={avatarPreview || ""} alt="Avatar preview" />
          <AvatarFallback>{avatarFile ? avatarFile.name : "NA"}</AvatarFallback>
        </Avatar>
        <Input
          id="avatar"
          name="avatar"
          type="file"
          accept="image/*"
          onChange={handleAvatarChange}
          required
        />
      </div>
      {error && <p className="text-red-500 text-sm">{error}</p>}
      <Button type="submit" className="w-full" disabled={isLoading}>
        {isLoading ? "Saving..." : "Save Profile"}
      </Button>
    </form>
  );
};

export default CompleteProfileForm;
