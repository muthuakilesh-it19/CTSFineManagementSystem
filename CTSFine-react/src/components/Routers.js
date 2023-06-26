import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Home from '../pages/Home';
import UserDashboard from '../pages/UserDashboard';
import OfficerDashboard from '../pages/OfficerDashboard';
import UserProfile from '../pages/UserProfile';
import OfficerProfile from '../pages/OfficerProfile';
export default function Routers() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" />} />
      <Route path="/home" element={<Home />} />
      <Route path="/userdashboard" element={<UserDashboard />} />
      <Route path="/officerdashboard" element={<OfficerDashboard />} />
      <Route path="/userprofile" element={<UserProfile />} />
      <Route path="/officerprofile" element={<OfficerProfile />} />
      
    </Routes>
  );
}
