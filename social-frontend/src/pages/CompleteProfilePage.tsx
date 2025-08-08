import CompleteProfileForm from "@/components/CompleteProfileForm";

const CompleteProfilePage = () => {
  const handleCompleteProfile = (data: { username: string; bio?: string }) => {
    console.log("Complete Profile:", data);
    // Add your profile completion logic here
  };

  return <CompleteProfileForm onSubmit={handleCompleteProfile} />;
};

export default CompleteProfilePage;
