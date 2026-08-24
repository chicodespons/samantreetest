# Talent Hub - Frontend implementation proposal

## General approach

I would build the application with React and TypeScript. 
React fits this screen well because it is made of reusable parts, 
and TypeScript helps keep the data models and component props clear when the application grows.

I would use:

- Vite for the project setup and build
- React Router for navigation
- TanStack Query for API calls, caching, loading and error states
- tailwindcss for styling or a library like MUI 
- Vitest Library for tests
- No state management library like Zustand or Redux for the moment. I would use React Context instead if needed. 
To reduce the complexity of the application.

## Page structure

The main layout can be separated into:

- `Sidebar`
- `Header`
- `DashboardPage`
    - `UserCarousel`
    - `UserCard`
    - `UserDetails`
    - `SkillList`
    - `SkillRow`

Small generic components such as `SearchInput`, `Avatar`, `IconButton`, `Badge`, `Card`, `Spinner` and `Modal` should be shared instead of copied between features.

I would organize the code by feature:

```text
src/
  components/       # shared UI components
  layout/           # sidebar and header
  features/
    users/
      components/
      api/
      types.ts
    skills/
      components/
      api/
      types.ts
  pages/
  routes/
  styles/
```


## Data and behaviour

The users and skills should come from an API. The API calls would be kept in separate service files so components only handle display and user interaction.

The selected user should be stored in the URL, for example `/users/123`. This makes refreshing the page and sharing a direct link work correctly.

The two search fields have different purposes:

- the search in the header searches users and skills globally
- the search in the skills panel only filters available skills

The carousel can initially load a limited number of users and fetch more when needed. 
Clicking a user updates the details and skills sections below it.

I would also include loading and error states. These are not visible in the mockup but are needed for a real application.

## Responsive design and accessibility
Make the application responsive. Ask the UI/UX team if they have designs for mobile and tablet.
This because a carousel is not a good fit for mobile.

 Icon-only buttons need labels
 form fields need labels
 The selected user should not be indicated only by a blue border, I find this not clear enough, maybe add a checkmark.
 When editing a user, the modal should be centered on the screen and selecting another user should not be possible.

## Points to confirm with UI/UX and backend

- What exactly happens when the `+` button next to a skill is clicked?
- How do the skills work? I find this the most confusing part of the application.
- Are the skills shown already assigned to the selected user, or are they suggestions?
- Who is allowed to edit a user or add skills?
- Should search start while typing or only after submitting?
- What should be shown when there are no users, skills or search results?
- What is the expected mobile layout?
- what happens on edit user information => does a modal appear?


## Testing

I would test the important behaviour instead of every visual detail:

- selecting a user updates the page
- searches send the correct query and show the results
- adding and editing data works and handles API errors
- loading and empty states are displayed

## Implementation order

1. Create the layout and reusable UI components.
2. Implement the user list and user details with mock data.
3. Connect the API and add loading/error handling.
4. Add edit and skill actions, responsive styling and tests.

I would first confirm the open UX and API questions, 
especially the meaning of the available skills list and the permissions. 
These have a big impact on the implementation.
