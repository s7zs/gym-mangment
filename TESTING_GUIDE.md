# Testing Guide - CommonServices with Factory Pattern

## 🎯 Quick Start

### Run All Tests
```bash
cd "d:\gym mangment\untitled"
mvn clean compile
mvn exec:java -Dexec.mainClass="services.CommonServicesDemo"
```

---

## 📊 What Gets Tested

### CommonServicesDemo.java - Comprehensive Test Suite

#### 1. **Factory Pattern Implementation** ✅
- **Method 1**: Direct factory + manual save
  ```java
  users member = Userfactory.createUser(Roles.member, "john_doe", "password123");
  userRepo.create(member);
  ```
- **Method 2**: Integrated factory (Recommended)
  ```java
  userRepo.createUser(Roles.member, "john_doe", "password123");
  ```

#### 2. **User Creation Tests** (5 tests)
- ✅ Create member using factory
- ✅ Create trainer using factory
- ✅ Create receptionist using factory
- ✅ Create physiotherapist using factory
- ✅ Verify factory assigns correct roles

#### 3. **Login Tests** (2 tests)
- ✅ Successful login with valid credentials
- ✅ Failed login with invalid password

#### 4. **Profile Tests** (4 tests)
- ✅ Get existing user profile
- ✅ Get non-existent user profile (null check)
- ✅ Update member profile
- ✅ Update trainer profile
- ✅ Update receptionist profile

#### 5. **Password Tests** (4 tests)
- ✅ Change password successfully
- ✅ Verify new password works
- ✅ Reject wrong old password
- ✅ Reset password to default
- ✅ Verify reset password works

#### 6. **Logout Test** (1 test)
- ✅ Logout user successfully

#### 7. **Edge Cases** (4 tests)
- ✅ Update non-existent user (should fail)
- ✅ Wrong password in change password (should fail)
- ✅ Get profile for non-existent user (should return null)
- ✅ Reset password for non-existent user (should fail)

---

## 📈 Test Coverage Summary

| Category | Tests | Status |
|----------|-------|--------|
| Factory Pattern | 2 | ✅ |
| User Creation | 5 | ✅ |
| Login/Logout | 3 | ✅ |
| Profile Operations | 4 | ✅ |
| Password Operations | 4 | ✅ |
| Edge Cases | 4 | ✅ |
| **TOTAL** | **20+** | ✅ |

---

## 🏭 Factory Pattern Verification

### What's Tested

1. **Direct Factory Usage**
   ```java
   users user = Userfactory.createUser(Roles.member, "username", "password");
   // Verifies: Correct type created
   ```

2. **Integrated Factory (Userrepo)**
   ```java
   String id = userRepo.createUser(Roles.trainer, "username", "password");
   // Verifies: Factory used internally, ID returned
   ```

3. **Role Assignment**
   ```java
   users user = userRepo.getProfile("username");
   assert user.getRole() == Roles.member; // Correct role assigned
   ```

4. **All User Types**
   - ✅ Member
   - ✅ Trainer
   - ✅ Receptionist
   - ✅ Physiotherapist

---

## 🔍 Expected Test Output

```
===========================================
   CommonServices Usage Demo & Tests
===========================================

1. Creating new users using Factory Pattern...
   Two ways to create users with Factory Pattern:

   Method 1: Manual Factory Pattern
   ✓ Member created with ID: 67xxxxxxxxxxxxxxxxxxxxx

   Method 2: Integrated Factory Pattern (Recommended)
   ✓ Trainer created with ID: 67xxxxxxxxxxxxxxxxxxxxx
   ✓ Receptionist created with ID: 67xxxxxxxxxxxxxxxxxxxxx

✓ All users created successfully using Factory Pattern!

2. Testing Login...
✓ Login successful!
  - Username: john_doe
  - Role: member
✓ Login correctly rejected invalid password

3. Getting User Profile...
✓ Profile retrieved!
  - Username: jane_trainer
  - Role: trainer

4. Updating User Profile...
✓ Member profile updated!
  - Phone: 555-1234
  - Address: 123 Fitness Street
  - Membership: Premium
✓ Trainer profile updated!
  - Specialization: Cardio & Weight Training
  - Experience: 5 years
  - Salary: $50000.0
✓ Receptionist profile updated!
  - Phone: 555-9999
  - Experience: 2y

5. Changing Password...
✓ Password changed successfully!
✓ Login successful with new password!

6. Resetting Password...
✓ Password reset to default (password123)
✓ Login successful with reset password!

7. Logging Out...
User john_doe logged out successfully

8. Testing Edge Cases & Validations...
✓ Correctly rejected update for non-existent user
✓ Correctly rejected password change with wrong old password
✓ Correctly returned null for non-existent user profile
✓ Correctly rejected password reset for non-existent user

9. Factory Pattern Verification...
✓ Physiotherapist created using Factory Pattern (ID: 67xxxxxxxxxxxxxxxxxxxxx)
✓ Factory Pattern correctly assigned role: physiotherapist

===========================================
   All CommonServices Functions Tested!
===========================================
✓ User Creation (Factory Pattern - 2 methods)
✓ Login (success & failure cases)
✓ Logout
✓ Get Profile
✓ Update Profile (Member, Trainer, Receptionist)
✓ Change Password
✓ Reset Password
✓ Edge Cases (4 validation tests)
✓ Factory Pattern Verification
===========================================
Total Tests Passed: 20+
===========================================
```

---

## ✅ Success Criteria

All tests pass if you see:
- ✅ No exceptions thrown
- ✅ All checkmarks (✓) displayed
- ✅ "Total Tests Passed: 20+" at the end
- ✅ All user types created successfully
- ✅ Factory Pattern used for all creations
- ✅ Edge cases handled correctly

---

## 🚨 Troubleshooting

### MongoDB Connection Issues
```
Error: MongoTimeoutException
```
**Solution**: Make sure MongoDB is running on `localhost:27017`
```bash
# Start MongoDB
mongod --dbpath /path/to/data
```

### Compilation Errors
```
Error: cannot find symbol
```
**Solution**: Clean and recompile
```bash
mvn clean compile
```

### Test Failures
Check if:
1. MongoDB is running
2. Database connection string is correct
3. All dependencies are installed (`mvn install`)

---

## 📝 Code Quality Checks

### Linter Status
```bash
mvn clean compile
# Check output for warnings
```

**Current Status**: ✅ No linter errors

### Factory Pattern Compliance
- ✅ All user creation goes through `Userfactory`
- ✅ Repository integrates factory pattern
- ✅ No direct `new User()` calls in application code

---

## 🎓 What You're Testing

### Design Patterns
1. **Factory Pattern** - Verified user creation
2. **Repository Pattern** - Database operations
3. **DTO Pattern** - Data transfer
4. **Service Layer** - Business logic

### Best Practices
1. **Separation of Concerns** - Each class has single responsibility
2. **DRY (Don't Repeat Yourself)** - Factory centralizes creation
3. **Error Handling** - Edge cases tested
4. **Validation** - Invalid inputs rejected

---

## 📚 Related Documentation

- `FACTORY_PATTERN_GUIDE.md` - Complete factory pattern guide
- `IMPLEMENTATION_SUMMARY.md` - Overall implementation details
- `CommonServicesDemo.java` - Test implementation source code

---

## 🎯 Next Steps

1. ✅ Run the demo to verify all tests pass
2. ✅ Review the test output
3. ✅ Understand the factory pattern usage
4. ✅ Study the code examples
5. ✅ Try modifying tests to understand behavior

---

## ⚡ Quick Commands

```bash
# Compile project
mvn compile

# Run tests
mvn exec:java -Dexec.mainClass="services.CommonServicesDemo"

# Clean and rebuild
mvn clean install

# Run with debug
mvn exec:java -Dexec.mainClass="services.CommonServicesDemo" -X
```

---

**Status**: ✅ **READY TO TEST**  
**Test File**: `CommonServicesDemo.java`  
**Total Tests**: 20+  
**All Systems**: GO ✅

Run the demo now to see your Factory Pattern in action!

