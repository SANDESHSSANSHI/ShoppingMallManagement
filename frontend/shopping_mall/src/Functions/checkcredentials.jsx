import { setuser, getuser } from "./userdata";

async function checkCredentials(username, password, userType) {
  console.log("Checking credentials for:", username);

  const URL = `/users/n/${username}`;

  try {
    const response = await fetch(URL);
    
    // Check if the response is successful
    if (!response.ok) {
      console.error(`Error: Failed to fetch user data. Status: ${response.status}`);
      return undefined;
    }

    const userDetails = await response.json();

    if (userDetails) {
      console.log("User details:", JSON.stringify(userDetails));
      console.log("Provided password:", password, "Expected password:", userDetails.password);
      console.log("Provided name:", username, "Expected name:", userDetails.name);
      console.log("Provided type:", userType, "Expected name:", userDetails.type);

      // Check if password and user type match
      if (password == userDetails.password && userType == userDetails.type) {
        console.log("Login successful for user ID:", userDetails.id);

        // Store user details for future reference
        setuser(userDetails);

        return [userDetails.id, userDetails.type];
      } else {
        console.warn("Invalid password or user type.");
        return undefined;
      }
    } else {
      console.warn("User not found.");
      return undefined;
    }
  } catch (error) {
    console.error("An error occurred during login:", error);
    return undefined;
  }
}

export default checkCredentials;
