import { useState, useEffect } from "react";
import { styled } from "@mui/material/styles";
import { Container, Card, CardContent, Typography, CircularProgress } from '@mui/material';
import { getExperienceByPersonalId } from "../services/ExperienceService";
import { usePersonalId } from "../context/PersonalIdContext";

const ExperiencesCard = () => {
  const personalId = usePersonalId();
  const [experienceData, setExperienceData] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchExperienceData = async () => {
      setLoading(true);
      try {
        const data = await getExperienceByPersonalId(personalId);
        setExperienceData(data);
      } catch (error) {
        setError("Connection is failure " + error);
      } finally {
        setLoading(false);
      }
    };
    if (personalId) {
      fetchExperienceData();
    }
  }, [personalId]);

  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardContent>
        {/* {loading ? (
        <Typography>Loading Experience Data...</Typography>
      ) : error ? (
        <Typography color="error">{error}</Typography>
      ) : experienceData.length > 0 ? (
        experienceData.map((experience) => {
          console.log("Rendering experience: ", experience);
          return (
            <div key={experience.idExperience}>
              <Typography variant="h6">{experience.jobTitle}</Typography>
              <Typography>{experience.companyName}</Typography>
              <Typography>
                {new Date(experience.startDate).toLocaleDateString()} -{" "}
                {new Date(experience.endDate).toLocaleDateString()}
              </Typography>
              </div>
            );
          })
        ) : (
          <Typography>Tidak ada data pengalaman ditemukan.</Typography>
        )} */}
        {loading ? (
          <CircularProgress style={{ alignItems: "center" }} />
        ) : error ? (
          <Typography variant="body2" color="error">
            {error}
          </Typography>
        ) : experienceData ? (
          <Typography
            variant="h5"
            component="div"
            style={{ textAlign: "center", fontFamily: "Poppins, sans-serif" }}
          >
            {experienceData.companyName} {experienceData.jobTitle}
          </Typography>
        ) : (
          <Typography variant="body2" color="text.secondary">
            Data Not Found
          </Typography>
        )}
      </CardContent>
    </Card>
  );
};

export default ExperiencesCard;
